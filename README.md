# LZTmusketeer
悬浮拖拽按钮第一版
## 根目录build.gradle中
![Image](https://images2018.cnblogs.com/blog/1258190/201807/1258190-20180701134240421-1015611465.gif)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
## app目录下build.gradle中添加
```
 dependencies {
            implementation 'com.github.hahage001:LZTmusketeer:v1.0.0'
    }
```
## 使用
需要注意的是mus.cn...这个路径是我本地的，与添加依赖有所不同。直接复制不可以使用。
```
<mus.cn.suspendbutton.DragFloatActionButton
        android:layout_width="200px"
        android:layout_height="200px"
        android:src="@mipmap/ic_launcher"
        app:backgroundTint="#253aac"
        app:rippleColor="#33728dff"
        app:elevation="6dp"
        app:pressedTranslationZ="12dp"
        android:clickable="true"
        android:focusable="true"
    />
```

```
```
