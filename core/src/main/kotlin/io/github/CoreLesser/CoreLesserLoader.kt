package io.github.CoreLesser

import com.badlogic.gdx.Gdx
import com.kotcrab.vis.ui.VisUI
import io.github.CoreLesser.manager.I18NManager
import io.github.CoreLesser.screen.MainMenuScreen
import io.github.CoreLesser.ui.GameSkin
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync
import ktx.collections.GdxArray

class CoreLesserLoader : KtxGame<KtxScreen>() {
    // 定义一个屏幕列表
    private val screenList = GdxArray<KtxScreen>()

    override fun create() {
        KtxAsync.initiate()
        VisUI.load()

        addScreen(MainMenuScreen(this))
        setScreen<MainMenuScreen>()

        GameSkin.load(I18NManager.getLanguage())
    }

    override fun <Type : KtxScreen> addScreen(type: Class<Type>, screen: Type) {
        if (screenList.any { it::class == screen::class }) {
            Gdx.app.log("CoreLesser","屏幕管理器：屏幕已添加。")
        } else {
            super.addScreen(type, screen)
        }
    }

    override fun dispose() {
        I18NManager.dispose()
        GameSkin.dispose()
        super.dispose()
    }
}
/*
class FirstScreen : KtxScreen {
    private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
    private val batch = SpriteBatch()

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        batch.use {
            it.draw(image, 100f, 160f)
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}*/
