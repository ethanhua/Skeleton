# Skeleton

The library provides an easy way to show skeleton loading view like Facebook and Alipay. It now uses a memory optimised version of shimmer animation so it is even faster and you can animate bigger layouts as well.

# Preview


![img](screenshots/01.gif)
![img](screenshots/02.gif)
![img](screenshots/03.gif)
![img](screenshots/04.gif)

# Demo Apk

you can scan the qrcode for download demo apk

![](screenshots/qrcode.png)

# Feature
- Light
- Different colors can be set for the shimmer effect
- Noninvasive, you don't need to make changes to existing code.
- Wide applicability，it is available for all views
- Memory optimised

# Getting started

In your build.gradle:

    dependencies {
       compile 'com.ethanhua:skeleton:0.2.2'
    }
    

# Usage
  For RecyclerView:
 
       skeletonScreen = Skeleton.bind(recyclerView)
                                .adapter(adapter)
                                .load(R.layout.item_skeleton_news)
                                .count(10)
                                .color(R.color.shimmer_color)
                                .show();
  For View: 
   
       skeletonScreen = Skeleton.bind(rootView)
                                .load(R.layout.layout_img_skeleton)
                                .color(R.color.shimmer_color)
                                .show();
                                
  when data return you can call the method to hide skeleton loading view 
   
       skeletonScreen.hide()
        
 # Thanks
 
 https://github.com/team-supercharge/ShimmerLayout

 https://github.com/sharish/ShimmerRecyclerView

 # License
 
    Copyright 2017, ethanhua
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
