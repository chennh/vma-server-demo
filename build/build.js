let path = require('path')
let utils = require('./utils')

const targetProjectName = '{{projectName}}'
const dbName = '{{dbName}}'
const excludeRegs = [
  // IDEA配置文件
  /.iml$/,
  // target目录
  /target/,
  // 所有隐藏文件
  /\/\./,
  // build目录
  /\/build\//
]
const projectNames = {
  'vma-server-demo': `vma-server-${targetProjectName}`,
  'server-model-xxx-demo': `server-model-${dbName}-${targetProjectName}`,
  'app-web-demo': `app-web-${targetProjectName}`,
  'app-business-service-demo': `app-business-service-${targetProjectName}`,
  'app-business-gateway-demo': `app-business-gateway-${targetProjectName}`,
  'app-cloud-service-demo': `app-cloud-service-${targetProjectName}`,
  'app-cloud-server-demo': `app-cloud-server-${targetProjectName}`,
  'app-cloud-client-demo': `app-cloud-client-${targetProjectName}`,
  'server-task-demo': `server-task-${targetProjectName}`,
  'server-common-demo': `server-common-${targetProjectName}`,
  'server-common-business-demo': `server-common-business-${targetProjectName}`,
  'server-common-cloud-demo': `server-common-cloud-${targetProjectName}`,
  'server-config-demo': `server-config-${targetProjectName}`
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
  java: new FileHandle(file = > /.java$ /
.
test(file), text =
>
text.replace(/com.vma.app.demo/igm, `com.vma.app.${targetProjectName}`).replace(/com.vma.model.xxx/igm, `com.vma.model.${dbName}`)
),
  // Spring启动文件配置
springFactories: new FileHandle(file = > / spring.factories$ /
.
test(file), text =
>
text.replace(/com.vma.app.demo/igm, `com.vma.app.${targetProjectName}`)
)
}

// copy工程
let files = utils.copyProject(path.resolve('{{source}}'), path.resolve('{{dist}}'), excludeRegs, file => {
  // 替换\ => /
  file = file.replace(/\\/g, '/')
  // 替换工程名
  file = file.replace(/vma-server-demo/, `vma-server-${targetProjectName}`)
  // 替换项目名
  file = file.replace(/((app|server)-.+-demo)/, (arg0) => {
    let arr = arg0.split('/')
    return arr[0] + '/' + projectNames[arr[1]]
  })
  // 替换目录名
file = file.replace(/(com\/vma\/app\/)demo/, `$1${targetProjectName}`)
  // 替换model名
  file = file.replace(/(com\/vma\/model\/)xxx/, `$1${dbName}`)
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
