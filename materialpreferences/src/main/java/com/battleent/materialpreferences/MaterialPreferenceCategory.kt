
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 battleent
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.battleent.materialpreferences

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.preference.PreferenceCategory
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

class MaterialPreferenceCategory : PreferenceCategory {

    private lateinit var title: TextView
    private lateinit var parent: RelativeLayout

    private var titleColor: Int = Color.BLACK
    private var titleSize: Float = 16f

    private var padding_left: Float = 16f
    private var padding_top: Float = 10f
    private var padding_right: Float = 0f
    private var padding_bottom: Float = 10f

    private var background: Int = Color.WHITE

    /**
     * Construct a new MaterialPreferenceCategory with default style options.
     *
     * @param context The Context that will style this preference
     */
    constructor(context: Context): super(context) {
        onCreate()
    }

    /**
     * Construct a new MaterialPreferenceCategory with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onCreate()
        getAttrs(attrs)
    }

    /**
     * Construct a new MaterialPreferenceCategory with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *        reference to a style resource that supplies default values for
     *        the view. Can be 0 to not look for defaults.
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onCreate()
        getAttrs(attrs, defStyleAttr)
    }

    /**
     * Construct a new MaterialPreferenceCategory with the given style options.
     *
     * @param attrs Style attributes that differ from the default
     */
    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialPreferenceCategory)
        setTypeArray(typedArray)
    }

    /**
     * Construct a new MaterialPreferenceCategory with the given style options.
     *
     * @param attrs Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *        reference to a style resource that supplies default values for
     *        the view. Can be 0 to not look for defaults.
     */
    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialPreferenceCategory, defStyleAttr, 0)
        setTypeArray(typeArray)
    }

    /**
     * Set default Styleable values.
     */
    private fun onCreate() {
        this.titleColor = ContextCompat.getColor(context, R.color.black_87)
    }

    /**
     * A {@link Preference} that provides a two-state toggleable option.
     *
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_title_color
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_title_size
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_background
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_padding_left
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_padding_top
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_padding_right
     * @attr ref android.R.styleable#MaterialPreferenceCategory_pref_category_padding_bottom
     */
    private fun setTypeArray(typeArray: TypedArray) {
        try {
            this.titleColor = typeArray.getColor(R.styleable.MaterialPreferenceCategory_pref_category_title_color, titleColor)
            this.titleSize = typeArray.getDimension(R.styleable.MaterialPreferenceCategory_pref_category_title_size, titleSize)
            this.background = typeArray.getColor(R.styleable.MaterialPreferenceCategory_pref_category_background, background)
            this.padding_left = typeArray.getDimension(R.styleable.MaterialPreferenceCategory_pref_category_padding_left, getDp(padding_left.toInt()).toFloat())
            this.padding_top = typeArray.getDimension(R.styleable.MaterialPreferenceCategory_pref_category_padding_top, getDp(padding_top.toInt()).toFloat())
            this.padding_right = typeArray.getDimension(R.styleable.MaterialPreferenceCategory_pref_category_padding_right, getDp(padding_right.toInt()).toFloat())
            this.padding_bottom = typeArray.getDimension(R.styleable.MaterialPreferenceCategory_pref_category_padding_bottom, getDp(padding_bottom.toInt()).toFloat())
        } finally {
            typeArray.recycle()
        }
    }

    /**
     * Set initial values.
     *
     * <p>
     *     it initialize styleable data
     */
    override fun onBindView(view: View) {
        super.onBindView(view)
        view.setBackgroundColor(background)
        title = view.findViewById(android.R.id.title)
        title.setPadding(padding_left.toInt(), padding_top.toInt(), padding_right.toInt(), padding_bottom.toInt())
        title.setTextColor(titleColor)
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
        title.setTypeface(title.typeface, Typeface.BOLD)

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 0, 0, 0)
        title.layoutParams = params
    }

    /**
     * Get DP value from PX.
     */
    private fun getDp(size: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (size * scale + 0.5f).toInt()
    }

    fun getTitleView() = this.title

    fun getParentView() = this.parent
}
