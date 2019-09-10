let path = require('path')
let utils = require('./utils')

const targetProjectName = '{{projectName}}'
const excludeRegs = [
  // IDEA配置文件
  /.iml$/,
  // target目录
  /target/,
  // 所有隐藏文件
  /\/\./,
  // build目录
  /vma-server-demo\/build/
]
const projectNames = {
  'vma-server-demo': `vma-server-${targetProjectName}`,
  'app-model-xxx-demo': `app-model-xxx-${targetProjectName}`,
  'app-web-demo': `app-web-${targetProjectName}`,
  'app-business-service-demo': `app-business-service-${targetProjectName}`,
  'app-business-gateway-demo': `app-business-gateway-${targetProjectName}`,
  'app-cloud-service-demo': `app-cloud-service-${targetProjectName}`,
  'app-cloud-server-demo': `app-cloud-server-${targetProjectName}`,
  'app-cloud-client-demo': `app-cloud-client-${targetProjectName}`,
  'app-task-demo': `app-task-${targetProjectName}`,
  'app-common-demo': `app-common-${targetProjectName}`,
  'app-common-business-demo': `app-common-business-${targetProjectName}`,
  'app-common-cloud-demo': `app-common-cloud-${targetProjectName}`,
  'app-config-demo': `app-config-${targetProjectName}`
}

class FileHandle {
  constructor(reg, handle) {
    this.reg = reg
    this.handle = handle
    this.files = []
  }
  match(file) {
    if (this.reg(file)) {
      this.files.push(file)
    }
  }
}
const fileHandles = {
  // java文件
  java: new FileHandle(file => /.java$/.test(file), text => text.replace(/com.vma.demo/igm, `com.vma.${targetProjectName}`)),
  // Spring启动文件配置
  springFactories: new FileHandle(file => /spring.factories$/.test(file), text => text.replace(/com.vma.demo/igm, `com.vma.${targetProjectName}`))
}

// copy工程
let files = utils.copyProject(path.resolve('../../vma-server-demo'), path.resolve('./dist'), excludeRegs, file => {
  // 替换工程名
  file = file.replace(/vma-server-demo/, `vma-server-${targetProjectName}`)
  // 替换项目名
  file = file.replace(/(app-.+-)demo/, `$1${targetProjectName}`)
  // 替换目录名
  file = file.replace(/(com\/vma\/)demo/, `$1${targetProjectName}`)
  return file
})

let fileHandlesKeys = Object.keys(fileHandles)
let pomFiles = []
files.forEach(file => {
  if (/pom.xml$/.test(file)) {
    pomFiles.push(file)
  }
  fileHandlesKeys.forEach(key => fileHandles[key].match(file))
})

// 更新pom
utils.updatePoms(pomFiles, projectNames)
// 更新文件
fileHandlesKeys.forEach(key => {
  let fileHandle = fileHandles[key]
  if (fileHandle.files.length) {
    utils.updateFiles(fileHandle.files, fileHandle.handle)
  }
})
