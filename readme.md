# MiraiLsys

<div class="BorderGrid-cell">
              <h2 class="h4 mb-3">Languages</h2>
<div class="mb-2">
  <span data-view-component="true" class="Progress">
    <span style="background-color:#b07219 !important;;width: 53.2%;" itemprop="keywords" aria-label="Java 53.2" data-view-component="true" class="Progress-item color-bg-success-inverse"></span>
    <span style="background-color:#A97BFF !important;;width: 46.6%;" itemprop="keywords" aria-label="Kotlin 46.6" data-view-component="true" class="Progress-item color-bg-success-inverse"></span>
    <span style="background-color:#C1F12E !important;;width: 0.2%;" itemprop="keywords" aria-label="Batchfile 0.2" data-view-component="true" class="Progress-item color-bg-success-inverse"></span>
</span></div>
<ul class="list-style-none">
    <li class="d-inline">
      <a class="d-inline-flex flex-items-center flex-nowrap Link--secondary no-underline text-small mr-3" href="/Kloping/MiraiLsys/search?l=java" data-ga-click="Repository, language stats search click, location:repo overview">
        <svg style="color:#b07219;" aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-dot-fill mr-2">
    <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8z"></path>
</svg>
        <span class="color-fg-default text-bold mr-1">Java</span>
        <span>53.2%</span>
      </a>
    </li>
    <li class="d-inline">
      <a class="d-inline-flex flex-items-center flex-nowrap Link--secondary no-underline text-small mr-3" href="/Kloping/MiraiLsys/search?l=kotlin" data-ga-click="Repository, language stats search click, location:repo overview">
        <svg style="color:#A97BFF;" aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-dot-fill mr-2">
    <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8z"></path>
</svg>
        <span class="color-fg-default text-bold mr-1">Kotlin</span>
        <span>46.6%</span>
      </a>
    </li>
    <li class="d-inline">
      <a class="d-inline-flex flex-items-center flex-nowrap Link--secondary no-underline text-small mr-3" href="/Kloping/MiraiLsys/search?l=batchfile" data-ga-click="Repository, language stats search click, location:repo overview">
        <svg style="color:#C1F12E;" aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-dot-fill mr-2">
    <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8z"></path>
</svg>
        <span class="color-fg-default text-bold mr-1">Batchfile</span>
        <span>0.2%</span>
      </a>
    </li>
</ul>
</div>

### 分离式插件

- 特点
    - 功能分离
    - 配置齐全
    - 扩展方便

主插件 : [a1-miraiLSys.jar](https://github.com/Kloping/MiraiLsys/releases/download/0.1.2/a1-miraiLSys.jar)

若无极端情况 请不要修改 文件名 否则将无法正常工作

主插件自带功能

- 开关操作
    - 开启 #开启此群
    - 关闭 #关闭此群
    - 开启私聊 #开启所有的私聊
    - 关闭私聊 #关闭所有私聊
- 积分相关
    - 积分查询 #字意
    - 查询积分 #字意
    - 取积分 xx #字意
    - 存积分 xx #字意

### 你以为就这就这了吗,不你错了

### 请看配置文件

配置文件位置将在 ./conf/Lsys 下
[配置文件说明](https://github.com/Kloping/MiraiLsys/tree/master/conf)
<details> 
<summary>配置文件</summary> 

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

</details>


对的 全可配置 可修改 触发,相应词

使用console命令

#### /Lsys reload #可重新加载配置

#### /Lsys setMain=? #可设主人QQ

## **子插件**

- 点歌的 [z2-Lsys-GetSong ](https://github.com/Kloping/MiraiLsys/tree/master/Lsys-GetSong)
- 发图的[z2-Lsys-GetPic ](https://github.com/Kloping/MiraiLsys/tree/master/Lsys-GetPic)
- 解析图片[Lsys-PicParser](https://github.com/Kloping/MiraiLsys/tree/master/Lsys-PicParser)
  (后续将继续更新)

#### 值得注意的是 若 未安装 主插件

#### 则 子插件也将无效

#### 加载 子插件 配置文件也会多出 相关的信息

例如
<details> 
<summary>配置文件</summary> 

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

</details>

若你对该系统感兴趣 可联系我 <br>
本人 QQ:[3474006766](http://wpa.qq.com/msgrd?v=3&uin=3474006766&site=qq&menu=yes)