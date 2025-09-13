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

    // 加载所有字符
    fun loadAllFont(fontSize: Int) : BitmapFont {
        return loadChinese(fontSize)
    }

    // 加载中文
    fun loadChinese(fontSize : Int) : BitmapFont {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/SourceHanSansSC-Light.otf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = fontSize
            mono = true
            incremental = true
        }
        return generator.generateFont(parameter)
    }

    // 释放资源
    override fun dispose() {}
}
