# Linux 杂

禁用休眠

```bash
systemctl mask sleep.target suspend.target hibernate.target hybrid-sleep.target
```

循环执行命令

```bash
while true ;do <command>; done;
while true ;do echo "hello"; sleep 1; done;
```

Get请求

```bash
curl "URL"
```
