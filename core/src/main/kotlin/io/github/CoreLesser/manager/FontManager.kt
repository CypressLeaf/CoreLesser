package io.github.CoreLesser.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Disposable
import com.kotcrab.vis.ui.VisUI

// 字符管理器
object FontManager : Disposable {
    // 定义UI皮肤
    private lateinit var skin : Skin
    // 加载中文
    fun loadChinese() : BitmapFont {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/03TogeGothic-SemiLight-2.otf"))
        val chineseParameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 64
            mono = false
            incremental = true
        }
        return generator.generateFont(chineseParameter)
    }

    // 直接加载所有字符
    fun loadAllFontToSkin() : Skin {
        skin = Skin()
        skin.add("chineseFont",loadChinese())
        skin.load(Gdx.files.internal("ui/uiskin.json"))
        return skin
    }

    // 释放资源
    override fun dispose() {}
}
