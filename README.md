# PPTPAndroidProject
PPT播放控制
##<center>PPTPlay简介及其后续规划</center>
###1.软件介绍  
pptplay是一个方便我们通过智能终端演示PC端的ppt，截止2016年4月1号我们只做了一个demo。可以实现局域网播放ppt,而且原理特别简单，手机端通过udp协议发送一个字符串，然后传到pc上的服务端，pc机服务端根据指令实现相应的动作。当然这毫无安全限制。后期我们会做接入端校验。
###2.Android第一版规划
我们的第一个版本的Android端的设计图是这样的
其中扫描的二维码的内容是IP:port,然后手机和pc通过sokcket连接（UDP协议），当你点熊掌时是向上翻页，点鱼的时候向下翻页，亦有鱼和熊掌不可兼得之意
###3愿景
 第二版的计划是实现不限于局域网，可以在广域网上使用（TCP协议，毕竟UDP会被限制），并且会有加密措施，手机端可以自动识别所处的网络环境，然后自动选择最优的方案。广域网版本的需要一个服务器，把两个不能直接互连的网络打通，大致思路已经想好。<p>第三版的计划是实现同屏幕显示，手机可以显示电脑的屏幕然后在上面执行相应的操作，这个版本前期应该是以局域网为主。<p>第四版的是可以连接互联网，同时加入画笔功能

