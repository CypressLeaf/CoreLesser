package io.github.CoreLesser.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextButton
import ktx.app.KtxGame
import ktx.app.KtxScreen

class MainMenuScreen(
    private val game: KtxGame<KtxScreen>
) : KtxScreen {
    // 定义一个舞台
    private val stage : Stage = Stage(ExtendViewport(1280f,720f))
    // 定义一个table容纳UI
    private val rootTable : VisTable = VisTable()
    /***
     * 游戏标题
     * 战役按钮
     * 多人战役按钮
     * 教程按钮
     * 设置按钮
     * 退出按钮
     */
    private val gameTitle : VisLabel = VisLabel("")
    private val battleGameButton : VisTextButton = VisTextButton("")
    private val partyGameButton : VisTextButton = VisTextButton("")
    private val courseButton : VisTextButton = VisTextButton("")
    private val settingsButton : VisTextButton = VisTextButton("")
    private val exitButton : VisTextButton = VisTextButton("")

    // 初始化主菜单
    init {
        createMainMenu()
    }

    // 构建主菜单
    private fun createMainMenu() {
        rootTable.apply {
            setFillParent(true)
            bottom().left()
            defaults().pad(10f)
            padBottom(20f)
            padLeft(40f)
            add(gameTitle).width(600f).width(60f).row()
            add(battleGameButton).width(400f).height(40f).row()
            add(partyGameButton).width(400f).height(40f).row()
            add(courseButton).width(400f).height(40f).row()
            add(settingsButton).width(400f).height(40f).row()
            add(exitButton).width(400f).height(40f).row()
        }
        exitButton.apply {
            addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    Gdx.app.exit()
                }
            })
        }
        stage.addActor(rootTable)
    }


    // 设置输入为舞台
    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    // 清屏并设置背景颜色、渲染舞台
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.draw()
    }

    // 更新屏幕大小时更新舞台大小
    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width,height,true)
    }

    // 释放舞台
    override fun dispose() {
        stage.dispose()
    }
}
