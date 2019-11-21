# tdd-java-ch07-books-store

## Reference

[https://bitbucket.org/vfarcic/tdd-java-ch07-books-store.git](https://bitbucket.org/vfarcic/tdd-java-ch07-books-store.git)

## 测试步骤

+ 启动 `Books Store`

```bash
./run_books_store.sh
```

+ 启动测试

> 需要安装 `FirefoxDriver`，每次测试前需重启 `Books Store`

```bash
gradle clean test
```

+ 关闭 `Books Store`

```bash
docker rm -f books_store >/dev/null 2>&1
```
