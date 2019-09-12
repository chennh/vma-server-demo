const fs = require('fs')
const path = require('path')

/**
 * 递归循环出目录下的所有文件
 * @param {*} dir 目录路径
 * @param {*} excludeRegs
 * @param {*} allFiles 文件存储数组
 */
function loopFiles(dir, excludeRegs = [], allFiles = []) {
  if (!excludeRegs.some(excludeReg => excludeReg.test(dir))) {
    let stat = fs.statSync(dir)
    if (stat.isFile()) {
      allFiles.push(dir)
    } else if (stat.isDirectory()) {
      let files = fs.readdirSync(dir)
      files.forEach(fileName => {
        loopFiles(path.join(dir, fileName), excludeRegs, allFiles)
      })
    }
  }
  return allFiles
}

/**
 * 创建目录
 * @param {*} dir 
 */
function mkdirs(dir) {
  let exists = fs.existsSync(dir)
  if (!exists) {
    if (mkdirs(path.dirname(dir))) {
      fs.mkdirSync(dir)
    }
    return fs.existsSync(dir)
  }
  return exists
}

/**
 * 删除目录
 * @param {*} dir 
 */
function deleteDir(dir, onlyRemoveChildren = false) {
  if (fs.existsSync(dir)) {
    if (fs.statSync(dir).isDirectory()) {
      fs.readdirSync(dir).forEach(file => {
        deleteDir(path.join(dir, file))
      })
      if (!onlyRemoveChildren) {
        fs.rmdirSync(dir)
      }
    } else {
      fs.unlinkSync(dir)
    }
  }
}

/**
 * 拷贝文件
 * @param {*} source 
 * @param {*} target 
 */
function copyFile(source, target) {
  if (!fs.existsSync(target)) {
    mkdirs(path.dirname(target))
  }
  fs.writeFileSync(target, fs.readFileSync(source))
}

module.exports = {
  /**
   * 拷贝工程
   * @param {*} sourceDir 
   * @param {*} targetDir 
   * @param {*} excludeRegs 
   * @param {*} targetFileHandle 
   */
  copyProject(sourceDir, targetDir, excludeRegs, targetFileHandle = file => file) {
    let sourceDirParent = path.dirname(sourceDir)
    let sourceFiles = loopFiles(sourceDir, excludeRegs)
    if (excludeRegs && excludeRegs.length) {
      sourceFiles = sourceFiles.map(file => file.replace(sourceDir, ''))
    }
    if (!fs.existsSync(targetDir)) {
      fs.mkdirSync(targetDir)
    } else {
      deleteDir(targetDir, true)
    }

    return sourceFiles.map(file => {
      let targetFile = targetDir + targetFileHandle(file)
      copyFile(sourceDir + file, targetFile)
      return targetFile
    })
  },
  /**
   * 更新pom
   * @param {*} targetPoms
   * @param {*} projectNames 
   */
  updatePoms(targetPoms, projectNames) {
    targetPoms.forEach(targetPom => {
      let text = fs.readFileSync(targetPom, {
        encoding: 'utf-8'
      })
      Object.keys(projectNames).forEach(projectName => {
        let reg = new RegExp(`${projectName}`, 'igm')
        text = text.replace(reg, projectNames[projectName])
      })
      fs.writeFileSync(targetPom, text)
    })
  },
  /**
   * 更新文件内容
   * @param {*} targetFiles 
   * @param {*} targetFileHandle 
   */
  updateFiles(targetFiles, targetFileHandle = text => text) {
    targetFiles.forEach(targetFile => {
      let text = fs.readFileSync(targetFile, {
        encoding: 'utf-8'
      })
      fs.writeFileSync(targetFile, targetFileHandle(text))
    })
  }
}