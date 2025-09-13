package io.github.CoreLesser.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Disposable
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTextButton
import ktx.style.register

// 字符管理器
object FontManager : Disposable {
    // 当前皮肤
    private lateinit var currentSkin: Skin

    // 初始化字符管理器
    init {
        loadAllFontToSkin()
        // 确保VisUI使用当前皮肤
        if (!VisUI.isLoaded()) {
            VisUI.load(currentSkin)
        } else {
            VisUI.dispose()
            VisUI.load(currentSkin)
        }
    }

    // 将所有字体添加至皮肤
    fun loadAllFontToSkin() : Skin {
        currentSkin = Skin()
        val allFont = loadChinese()
        updateStylesWithFont(allFont)
        val atlas = TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        currentSkin.addRegions(atlas)
        currentSkin.add("default-font",allFont)
        currentSkin.add("font",allFont)
        currentSkin.load(Gdx.files.internal("ui/uiskin.json"))
        return currentSkin
    }

    // 更新VisUI样式
    fun updateStylesWithFont(font: BitmapFont) {
        try {
            val visTextButtonStyle = VisTextButton.VisTextButtonStyle().apply {
                this.font = font
            }
            currentSkin.add("default",visTextButtonStyle)
        } catch (e : Exception) {
            Gdx.app.error("字符管理器","找不到的VisUI样式：${e.message}")
        }
    }

    // 加载所有字符
    fun loadAllFont() : BitmapFont {
        return loadChinese()
    }

    // 加载中文
    fun loadChinese() : BitmapFont {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/SourceHanSansSC-Light.otf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 64
            mono = false
            incremental = true
        }
        return generator.generateFont(parameter)
    }


    // 获取当前皮肤（用于首次加载）
    fun getSkin() : Skin {
        return currentSkin
    }
    // 重新加载皮肤（用于语言切换）
    fun reloadSkin() {
        loadAllFontToSkin()
        if (VisUI.isLoaded()) {
            VisUI.dispose()
        }
        VisUI.load(currentSkin)
    }

    // 释放资源
    override fun dispose() {
        currentSkin.dispose()
        if (VisUI.isLoaded()) {
            VisUI.dispose()
        }
    }
}
