### superui
1. superui官方网站：http://www.supermgr.cn
3. superui官方一群:559696533
4. superui在线体验地址:http://www.supermgr.cn/demo/Admin/index.html

### superui是什么？
superui是tzhsweet、lkl、hwj三人联合开发的一款开源前端快速开发框架。
### superui有哪些优点？
1. **组件丰富，扩展强大**，大量优秀成熟开源组件，兼容性强，高扩展性。
2. **炫酷华丽，风格多样**，bootstrap风格UI，华丽而炫酷，组件风格和控制代码分离，风格千变万化！ 
3. **各种浏览器，全面兼容**，各种PC浏览器（IE8+、chrome、firefox、safari等)，chrome、uc等手机浏览器，微信，全面兼容，极致体验！
4. **标准化、组件化，**遵循html5+css3+js标准，兼容性好、扩展性强。

### superui是否需要收费？
superui完全免费，采用apache2.0协议！

### bug跟踪以及需求管理
superui采用worktile进行需求管理、项目迭代以及bug跟踪，如果有相关问题请直接在worktile中进行反馈。
worktile地址：https://worktile.com/project/9b3e47ed312942d4aa988ac1b63a93d6/task

### 版本跟踪
- SuperUI V1.1 alpha版本
1. 由于核心框架css进行了压缩（对于css中引用图片，采用了绝对路径处理，只有核心框架的css采用绝对路径），需要挂载在服务器上才能正常显示。
2. 内部组件进行了压缩和优化，只保留了基础插件，剩下的组件后续将陆续补充。
3. 核心superui.common.min.css大小为65kb，components.min.css为80kb（基础样式）,darkblue.min.css为4.5kb，superui.common.min.js为核心js框架，大小为100kb，
    css因为集成了bootstrap等，大小仍然比较大，后续将继续优化， js已经集成了十几个插件以及jquery、bootstrap等大量插件，打包工具采用gulp进行打包。
4. api文档近期开始着手编写。
5. 重构底层结构