# JesMVP

四月份进入新公司，Android业务进入公司之前还没有开展，为了方便开发构建了该MVP架构。实际项目中已将基础模块分离，以满足日后实现组件化开发，借助AS中的Module做业务分离，目前是分项目开发，未来做平台开发，将多个项目整合为一个平台，所以之后会实现路由分发页面跳转

### 结构 ###
	jesmvp
      ├─base //一些基础类
      │  ├─adapter 
      │  ├─bean
      │  ├─presenter
      │  └─view
      ├─bean //实体类
      ├─constants //常量
      ├─model //对model层的基础封装
      │  ├─cache //缓存封装
      │  ├─netapi //统一的API接口管理
      │  ├─retrofit //retrofit封装类
      │  └─subscribe //subscribe封装类
      ├─module //业务层
      │  └─login 
	  │      ├─contract //契约类 用于统一管理View和Presenter的接口
      │      ├─model //具体业务的model层
      │      ├─presenter //presenter层
      │      └─ui //View层
      ├─utils //工具类
      │  ├─encrypt //加密相关
      │  └─glide //glide工具类
      └─widget //UI组件类
          ├─dialog
          └─loadmore




- 将所有依赖的版本控制提取到config.gradle做统一管理


### 开源库 ###

-  supportVersion       : "25.3.1"
-  retrofit             : "2.1.0"
-  okhttp3_logging      : "3.4.2"
-  rxandroid            : "1.2.1"
-  rxjava               : "1.2.3"
-  butterknife          : "8.5.1"
-  glide                : "3.7.0"
-  glide_okhttp3        : "1.4.0@aar"
-  recycler_animators   : "2.1.0"
-  leakcanary           : "1.5.1"
-  gson                 : "2.7"
-  klog                 : "1.6.0"
-  statusBartUtils      : "1.3.6"
-  glide_transformations: "2.0.1"
-  constraint_layout    : "1.0.2"
-  permissionsdispatcher: "2.3.2"
-  hellocharts          : "v1.5.8"
-  hawk                 : "2.0.1"

### TODO ###

- 组件化：借助AS中的Module，项目中已经开始，找机会整理后提交
- 路由：支撑组件化后各业务Module的跳转以及参数控制

### 2017-9-18 ###

- 利用Hawk做数据缓存（Key-value）