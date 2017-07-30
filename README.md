# skeleton

a liabrary provider a easy way to show skeleton loading view 
![](https://github.com/ethanhua/Skeleton/blob/master/screenshots/1.gif)
![](https://github.com/ethanhua/Skeleton/blob/master/screenshots/2.gif)
![](https://github.com/ethanhua/Skeleton/blob/master/screenshots/3.gif)
![](https://github.com/ethanhua/Skeleton/blob/master/screenshots/4.gif)

# Getting started

In your build.gradle:

    dependencies {
       compile 'me.drakeet.multitype:multitype:3.1.0'
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
