package com.example.chayut.test

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), BaseRecyclerAdapter.StickerClickListener {
    override fun onStickerClick(stickerItem: StickerItem) {
        GlideApp.with(this)
                .load(stickerItem.full)
                .into(previewSticker)

        previewLayout.visibility = View.VISIBLE
    }


    private lateinit var footerView: View
    private lateinit var rootView: View

    private var diffHeight = 0
    private var lastDiffHeight = 0
    private var firstTime = true
    private var baseHeight: Int = 0
    private var keyboardToggleByBtn: Boolean = false

    private lateinit var btnToggleSticker: Button
    private lateinit var editText: EditText
    private lateinit var btnSubmit: Button

    private lateinit var previewLayout: View
    private lateinit var previewSticker: ImageView
    private lateinit var btnDismissPreview: Button

    private val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    private val json = "{\"pack\":[{\"name\":\"Collection1\",\"version\":1,\"icon\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/icon1.png?alt=media&token=8d6cc104-1079-4b16-8b22-7cc19dd79488\",\"sticker\":[{\"name\":\"a1\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a1.png?alt=media&token=a59bb8ea-0114-4bfb-ae50-1245fe22830d\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a1.png?alt=media&token=a59bb8ea-0114-4bfb-ae50-1245fe22830d\"},{\"name\":\"a2\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a2.png?alt=media&token=8d1ced81-ba45-4254-8d45-b9fe775cc94e\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a2.png?alt=media&token=8d1ced81-ba45-4254-8d45-b9fe775cc94e\"},{\"name\":\"a3\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a3.png?alt=media&token=f86c7d61-47f5-45aa-bf51-a8b23ab92dac\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a3.png?alt=media&token=f86c7d61-47f5-45aa-bf51-a8b23ab92dac\"},{\"name\":\"a4\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a4.png?alt=media&token=78a0b4de-57ff-4842-96ca-fca02dd59010\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a4.png?alt=media&token=78a0b4de-57ff-4842-96ca-fca02dd59010\"},{\"name\":\"a5\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a5.png?alt=media&token=bba763cc-ce97-41fc-88b2-4463489e5a7a\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/a5.png?alt=media&token=bba763cc-ce97-41fc-88b2-4463489e5a7a\"}]},{\"name\":\"Collection2\",\"version\":2,\"icon\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/icon2.png?alt=media&token=3eb701bc-3bef-4040-921f-0cdc6b8a5d9b\",\"sticker\":[{\"name\":\"b1\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b1.png?alt=media&token=5fae29eb-9dd7-4bf3-8c8a-d629dc5ea1f0\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b1.png?alt=media&token=5fae29eb-9dd7-4bf3-8c8a-d629dc5ea1f0\"},{\"name\":\"b2\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b2.png?alt=media&token=4babcd40-efba-4208-9b10-66fe5f3b67b2\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b2.png?alt=media&token=4babcd40-efba-4208-9b10-66fe5f3b67b2\"},{\"name\":\"b3\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b3.png?alt=media&token=0bd3f950-8261-4492-a1a4-fe8c60ef3082\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b3.png?alt=media&token=0bd3f950-8261-4492-a1a4-fe8c60ef3082\"},{\"name\":\"b4\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b4.png?alt=media&token=c4915a68-73c9-470b-bd15-83db59d92b04\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b4.png?alt=media&token=c4915a68-73c9-470b-bd15-83db59d92b04\"},{\"name\":\"b5\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b5.png?alt=media&token=1f223988-b229-44c2-ac9d-420ffd06ca67\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/b5.png?alt=media&token=1f223988-b229-44c2-ac9d-420ffd06ca67\"}]},{\"name\":\"Collection3\",\"version\":5,\"icon\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/icon3.png?alt=media&token=4fc2de09-d3b8-4346-8f69-de83c5c61398\",\"sticker\":[{\"name\":\"c1\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c1.png?alt=media&token=92d72ef2-185b-4080-ab04-9dc2e58c8c98\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c1.png?alt=media&token=92d72ef2-185b-4080-ab04-9dc2e58c8c98\"},{\"name\":\"c2\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c2.png?alt=media&token=ab3348f0-e64f-482c-97f7-71dffde1257b\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c2.png?alt=media&token=ab3348f0-e64f-482c-97f7-71dffde1257b\"},{\"name\":\"c3\",\"full\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c3.png?alt=media&token=a8a0abf2-c42b-44a6-9420-e1197af9966e\",\"thumb\":\"https://firebasestorage.googleapis.com/v0/b/nth-weft-108718.appspot.com/o/c3.png?alt=media&token=a8a0abf2-c42b-44a6-9420-e1197af9966e\"}]}]}"
    private val stickerDao = StickerDao().objectFromData(json)
    private val packIcon: ArrayList<String> = ArrayList()

    private lateinit var viewPage: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInstance()
        setupKeyboardEvent()
        setupStickerKeyboard()
    }

    private fun setupStickerKeyboard() {
        for (pack in stickerDao.pack!!) {
            viewPagerAdapter.addFragment(StickerFragment().newInstance(pack), pack?.name ?: "")
            packIcon.add(pack?.icon ?: "")
        }

        viewPage.adapter = viewPagerAdapter


        tabLayout.setupWithViewPager(viewPage)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        packIcon.forEachIndexed { index, s ->
            tabLayout.getTabAt(index)?.customView = createTabItemView(s)
        }
    }



    private fun initInstance() {
        setContentView(R.layout.activity_main)

        footerView = findViewById(R.id.footer)
        rootView = findViewById(R.id.root_view)

        btnToggleSticker = findViewById(R.id.btn_toggle_sticker)
        editText = findViewById(R.id.edit_text)
        btnSubmit = findViewById(R.id.btn_submit)
        previewLayout = findViewById(R.id.preview_layout)
        previewSticker = findViewById(R.id.iv_sticker_preview)
        btnDismissPreview = findViewById(R.id.dismiss_preview)

        viewPage = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        btnDismissPreview.setOnClickListener {
            previewLayout.visibility = View.GONE
        }
    }

    private fun setupKeyboardEvent() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        btnToggleSticker.setOnClickListener {
            keyboardToggleByBtn = true
            if (footerView.visibility == View.GONE) {
                if (diffHeight == baseHeight && lastDiffHeight == baseHeight && footerView.visibility == View.GONE) {
                    footerView.visibility = View.VISIBLE
                }
                toggleSoftKeyboard(true)
            } else {
                toggleSoftKeyboard(false)
            }
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView!!.getWindowVisibleDisplayFrame(rect)

            val screenHeight = rootView!!.rootView.height
            diffHeight = screenHeight - rect.bottom

            if (firstTime) {
                baseHeight = diffHeight
                firstTime = false
            } else {
                if (diffHeight < lastDiffHeight) {
                    if (keyboardToggleByBtn) {
                        footerView?.visibility = View.VISIBLE
                        keyboardToggleByBtn = false
                    } else {
                        footerView?.visibility = View.GONE
                    }

                }

                if (diffHeight >= baseHeight && lastDiffHeight < diffHeight) {
                    footerView?.visibility = View.GONE
                    keyboardToggleByBtn = false
                }
            }
            lastDiffHeight = diffHeight
        }
    }

    private fun toggleSoftKeyboard(hide: Boolean) {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (hide) {
                editText.clearFocus()
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            } else {
                imm.toggleSoftInputFromWindow(editText.windowToken, InputMethodManager.SHOW_FORCED, 0)
            }
        }
    }

    private fun createTabItemView(imgUri: String): ImageView {
        val imageView = ImageView(this@MainActivity)
        val layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT)

        imageView.layoutParams = layoutParams

        Glide.with(this@MainActivity)
                .load(imgUri)
                .into(imageView)
        return imageView
    }

    override fun onBackPressed() {
        if (footerView.visibility == View.VISIBLE)
            footerView.visibility = View.GONE
        else
            super.onBackPressed()
    }


}
