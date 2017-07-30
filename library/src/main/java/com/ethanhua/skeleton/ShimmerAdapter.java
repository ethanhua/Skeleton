/**
 *
 * Copyright 2017 Harish Sridharan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ShimmerAdapter extends RecyclerView.Adapter<ShimmerViewHolder> {

    private int mItemCount = 10;
    private int mLayoutReference ;


    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
    }

    @Override
    public ShimmerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ShimmerViewHolder(inflater, parent, mLayoutReference);
    }

    @Override
    public void onBindViewHolder(ShimmerViewHolder holder, int position) {
        ShimmerFrameLayout layout = (ShimmerFrameLayout) holder.itemView;
        layout.startShimmerAnimation();
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }



}
