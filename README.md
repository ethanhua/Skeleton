# skeleton

a library provider a easy way to show skeleton loading view 

![img](screenshots/001.gif)
![img](screenshots/002.gif)
![img](screenshots/003.gif)
![img](screenshots/004.gif)

# Demo Apk

you can scan the qrcode for download demo apk

![](screenshots/qrcode.png)

# Getting started

In your build.gradle:

    dependencies {
       compile 'com.ethanhua:skeleton:0.1.1'
    }
    
# Feature
- No invasive，You don't need to make changes to existing code.
- Wide applicability，It is available for all views
# Usage
  for recyclerview:
 
       skeletonScreen = Skeleton.bind(recyclerView)
                                .adapter(adapter)
                                .count(10)
                                .show(R.layout.item_skeleton_news);
  for view: 
   
       skeletonScreen = Skeleton.bind(rootView)
                                .show(R.layout.layout_img_skeleton);
                                
  when data return you can call the method to hide skeleton loading view 
   
       skeletonScreen.hide()
        
 # Thanks
 
 https://github.com/sharish/ShimmerRecyclerView
 
 https://github.com/facebook/shimmer-android
