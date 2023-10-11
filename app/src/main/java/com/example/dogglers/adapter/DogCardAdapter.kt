/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout.GRID
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int,
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dataset = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val imageView: ImageView? = view!!.findViewById(R.id.dog_image)
        val dogNameView: TextView? = view!!.findViewById(R.id.dog_name)
        val dogAgeView: TextView? = view!!.findViewById(R.id.dog_age)
        val dogHobbyView: TextView? = view!!.findViewById(R.id.dog_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val adapterLayout = when (layout) {
            // Inflate the layout
            GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dogData = dataset[position]
        // Set the image resource for the current dog
        holder.imageView?.setImageResource(dogData.imageResourceId)
        // Set the text for the current dog's name
        holder.dogNameView?.text = dogData.name
        // Set the text for the current dog's age
        val resources = context?.resources
        holder.dogAgeView?.text = resources?.getString(R.string.dog_age, dogData.age)
        // Set the text for the current dog's hobbies by passing the hobbies to the
        // R.string.dog_hobbies string constant.
        // Passing an argument to the string resource looks like:
        // resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.dogHobbyView?.text = resources?.getString(R.string.dog_hobbies, dogData.hobbies)
    }
}
