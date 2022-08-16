### MiraiLsys 子插件

### 积分相关的操作

下载

- 积分相关的 [z4-Lsys-Score1.jar ](https://github.com/Kloping/MiraiLsys/releases)
    - 同样若无极端情况请不要修改文件名 否则将无法正常工作

### 其命令

    1. 签到
    2. 猜拳 (xx)
    3. 打劫 @xx 

配置文件中: win 代表猜拳 赢得概率 ob 代表平局的概率

== update time on 21/12/9

- 增加Conf
  <details> 

<summary>配置文件</summary> 

```json
{
  //猜拳平局的概率 18%
  "ob": 18,
  //第一名签到获得的积分
  "signFirstGet": 220,
  //正常签到获得的积分
  "signNormalGet": 100,
  //第二名签到获得的积分
  "signSecondGet": 160,
  //第三名签到获得的积分
  "signThirdGet": 120,
  //猜拳赢的概率 18%
  "win": 40
}
```

</details>