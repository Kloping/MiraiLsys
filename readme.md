#MiraiLsys

###分离式插件 
- 特点 
  - 功能分离
  - 配置齐全
  - 扩展方便

 

主插件 : [a1-miraiLSys.jar](https://github.com/Kloping/MiraiLsys/releases/download/0.1/a1-miraiLSys.jar)

若无极端情况 请不要修改 文件名 否则将无法正常工作

主插件自带功能

- 开关操作 
  - 开启       #开启此群
  - 关闭       #关闭此群
  - 开启私聊    #开启所有的私聊
  - 关闭私聊    #关闭所有私聊
- 积分相关
  - 积分查询    #字意
  - 查询积分     #字意
  - 取积分 xx   #字意
  - 存积分 xx   #字意

###你以为就这就这了吗,不你错了
###请看配置文件
```json
{
  "qq": -1,
  "invokeGroups": {
    "main": {
      "id": "main",
      "invokes": {
        "积分查询": "m1",
        "查询积分": "m1",
        "取积分.*": "m2",
        "存积分.*": "m3",
        "开启": "mOpen",
        "关闭": "mClose",
        "开启私聊": "mpOpen",
        "关闭私聊": "mpClose"
      },
      "invokesAfter": {
        "关闭": [
          "<At = ?>\n关闭成功",
          "<At = ?>\n本就是关闭"
        ],
        "查询积分": [
          "<At = ?>\n剩的积分:$1\n存的积分$2"
        ],
        "开启私聊": [
          "<At = ?>\n开启成功"
        ],
        "开启": [
          "<At = ?>\n开启成功",
          "<At = ?>\n本就是开启"
        ],
        "积分查询": [
          "<At = ?>\n剩的积分:$1\n存的积分$2"
        ],
        "关闭私聊": [
          "<At = ?>\n关闭成功"
        ],
        "取积分.*": [
          "<At = ?>\n取积分成功",
          "<At = ?>\n取积分失败,存的积分不足",
          "<At = ?>\n格式错误"
        ],
        "存积分.*": [
          "<At = ?>\n存积分成功",
          "<At = ?>\n存积分失败,剩余积分不足",
          "<At = ?>\n格式错误"
        ]
      }
    }
  },
  "path": "./data/LSys",
  "prK": false,
  "opens": [
    -1
  ]
}
```

对的 全可配置 可修改 触发,相应词

使用console命令
####  /Lsys reload  #可重新加载配置

- 子插件
  - 点歌的 [z2-Lsys-GetSong.jar ](https://github.com/Kloping/MiraiLsys/releases/download/0.1/z2-Lsys-GetSong.jar)
    - 同样若无极端情况请不要修改文件名 否则将无法正常工作
    - 其命令 在群里 发送 "点歌系统" 即可查看
  - 发图的[z2-Lsys-GetSong.jar ](https://github.com/Kloping/MiraiLsys/releases/download/0.1/z1-Lsys-GetPic.jar)
    - 同样若无极端情况请不要修改文件名 否则将无法正常工作
    - 其命令 在群里 "发张 xxx" 即可 (后续更新更多)

#### 值得注意的是 若 未安装 主插件 
#### 则 孩子插件也将无效
#### 且 配置文件也会多出 相关的信息
例如 
```json
{
  "qq": -1,
  "invokeGroups": {
    "getPic": {
      "id": "getPic",
      "invokes": {
        "发张.*": "getPicOne"
      },
      "invokesAfter": {
        "发张.*": [
          "<Image = $1>",
          "获取失败"
        ]
      }
    },
    "main": {
      "id": "main",
      "invokes": {
        "积分查询": "m1",
        "查询积分": "m1",
        "取积分.*": "m2",
        "存积分.*": "m3",
        "开启": "mOpen",
        "关闭": "mClose"
      },
      "invokesAfter": {
        "关闭": [
          "<At = ?>\n关闭成功",
          "<At = ?>\n本就是关闭"
        ],
        "查询积分": [
          "<At = ?>\n剩的积分:$1\n存的积分$2"
        ],
        "积分查询": [
          "<At = ?>\n剩的积分:$1\n存的积分$2"
        ],
        "开启": [
          "<At = ?>\n开启成功",
          "<At = ?>\n本就是开启"
        ],
        "取积分.*": [
          "<At = ?>\n取积分成功",
          "<At = ?>\n取积分失败,存的积分不足",
          "<At = ?>\n格式错误"
        ],
        "存积分.*": [
          "<At = ?>\n存积分成功",
          "<At = ?>\n存积分失败,剩余积分不足",
          "<At = ?>\n格式错误"
        ]
      }
    },
    "getSong": {
      "id": "getSong",
      "invokes": {
        "酷狗点歌.*": "pointKugou",
        "QQ点歌.*": "pointQQ",
        "网易点歌.*": "pointWy",
        "点歌系统": "method"
      },
      "invokesAfter": {
        "网易点歌.*": [
          "<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>",
          "<At = ?>点歌失败"
        ],
        "QQ点歌.*": [
          "<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>",
          "<At = ?>点歌失败"
        ],
        "点歌系统": [
          "<At = ?>\n酷狗点歌 歌名\n网易点歌 歌名\nQQ点歌 歌名"
        ],
        "酷狗点歌.*": [
          "<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>",
          "<At = ?>点歌失败"
        ]
      }
    }
  },
  "path": "./data/LSys",
  "prK": false,
  "opens": [
    -1
  ]
}
```
若你对该系统感兴趣 可联系我 <br>
本人 QQ:[3474006766](http://wpa.qq.com/msgrd?v=3&uin=3474006766&site=qq&menu=yes)