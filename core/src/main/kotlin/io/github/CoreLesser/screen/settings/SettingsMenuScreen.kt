package io.github.CoreLesser.screen.settings

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.manager.I18NManager
import ktx.app.KtxGame
import ktx.app.KtxScreen

class SettingsMenuScreen(
    private val game: KtxGame<KtxScreen>
) : KtxScreen {
    // 定义一个舞台
    private val stage : Stage = Stage(ExtendViewport(1280f,720f))
    // 定义一个table容纳UI
    private val rootTable : VisTable = VisTable()
    /***
     * 语言选项
     */
    private val languageOptionsButton : VisTextButton = VisTextButton("")

    // 初始化设置菜单
    init {
        createSettingsMenu()
    }

    // 构建设置菜单
    fun createSettingsMenu() {
        stage.addActor(rootTable)
        rootTable.apply {
            add(languageOptionsButton).width(400f).height(40f).row()
        }
        languageOptionsButton.apply {
            setText(I18NManager.getString())
        }
    }
}
