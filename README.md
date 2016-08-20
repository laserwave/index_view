Android-IndexView
====================
![](https://github.com/laserwave/IndexView/blob/master/images/1.gif)

![](https://github.com/laserwave/IndexView/blob/master/images/2.gif)

This is used to add index to ListView.

Support `API LEVEL >= 16`.

Including In Your Project
-------------------------

#### Gradle
Add the following code in the build.gradle of your module.
```groovy
dependencies {
    compile 'cn.zhikaizhang.index:library:1.0.0'
}
```
#### Download the source code
You can also download the source code of the project and import the library module into your project as a module so that you can modify the source code.

Usage
-----
*For a application of the widget see the `sample/` folder.*

Step1. Include the widget and modify the appearance in your xml. Such as:

```xml
<cn.zhikaizhang.indexview.IndexView
    android:id="@+id/indexView"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_alignParentRight="true"
    app:heightOccupy="0.9"
    app:indexTextSizeScale="0.7"
    app:tipBg="@drawable/tip_bg"
    app:tipTextColor="#ffffff" />
```

All of the attributes are shown as :

```xml
<declare-styleable name="IndexView">
	<attr name="heightOccupy" format="float" />
	<attr name="indexTextColor" format="color" />
	<attr name="selectIndexTextColor" format="color" />
	<attr name="selectIndexBgColor" format="color" />
	<attr name="indexTextSizeScale" format="float" />
	<attr name="tipTextColor" format="color" />
	<attr name="tipBg" format="color|reference" />
</declare-styleable>
```

The description is shown as :

|attribute|description|
|:-:|:-:|
|heightOccupy|0.0f-1.0f, the percent of height used|
|indexTextColor|the color of indexes that are not selected|
|selectIndexTextColor|the color of index selected|
|selectIndexBgColor|the background color of index selected|
|indexTextSizeScale|0.0f-1.0f, control the text size of index, default is 0.65f|
|tipTextColor|the text color of tip|
|tipBg|the background of tip|


heightOccupy 


Step2. Sort the data of ListView.

``` java
List<Item> items = getData();
Collections.sort(items, new PinyinComparator<Item>() {
    @Override
    public int compare(Item s1, Item s2) {
        return compare(s1.getSong(), s2.getSong());
    }
});
```

Step3. Bind the IndexView and ListView in the java code. Such as:

```java
Binder binder = new Binder(listView, indexView) {
    @Override
    public String getListItemKey(int position) {
        return ((Item)(listView.getAdapter().getItem(position))).getSong();
    }
};
binder.bind();
```

The method `String getListItemKey(int position)` specify a string of the list item for sorting and indexing.


Author
======

 * ZhikaiZhang 
 * Email <zhangzhikai@seu.edu.cn>
 * Blog <http://zhikaizhang.cn>
 * [android自定义控件之索引控件的实现](http://zhikaizhang.cn/2016/08/20/android自定义控件之索引控件的实现/)

License
=======

    Copyright 2016 ZhikaiZhang 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

