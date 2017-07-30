# skeleton

a liabrary provider a easy way to show skeleton loading view 

<img src='screenshots/1.gif' height=444 width=250 />
<img src='screenshots/2.gif' height=444 width=250 />
<img src='screenshots/3.gif' height=444 width=250 />
<img src='screenshots/4.gif' height=444 width=250 />

you can scan the qrcode for download demo apk

![]()

# Getting started

In your build.gradle:

    dependencies {
       compile 'com.ethanhua:skeleton:0.1.0'
    }

# Usage
  for recyclerview:
 
       skeletonScreen = Skeleton.bind(recyclerView)
                                .adapter(adapter)
                                .count(10)
                                .placeHolder(R.layout.item_skeleton_news)
                                .show();
  for view: 
   
       skeletonScreen = Skeleton.bind(rootView)
                                .placeHolder(R.layout.layout_img_skeleton)
                                .show();
  when data return you could call 
   
       skeletonScreen.hide()
        
 # Thanks
 
 https://github.com/sharish/ShimmerRecyclerView
 
 https://github.com/facebook/shimmer-android
