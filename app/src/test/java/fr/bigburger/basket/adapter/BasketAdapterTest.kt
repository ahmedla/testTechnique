package fr.bigburger.basket.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.nhaarman.mockitokotlin2.any
import fr.bigburger.list.entity.Burger
import kotlinx.android.synthetic.main.item_burger.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer

class BasketAdapterTest {

    @Mock
    lateinit var itemView: View
    @Mock
    lateinit var titleTextView: TextView
    @Mock
    lateinit var context: Context

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun bindDataTest() {

        var basketHolder = BasketAdapter.BasketViewHolder(this.itemView)
        var text =""
        Mockito.`when`(this.titleTextView.setText(ArgumentMatchers.anyString())).thenAnswer(Answer { invocation -> text = invocation.arguments[0] as String })
        Mockito.`when`(this.titleTextView.getText()).thenAnswer(Answer { invocation -> text })
        Mockito.`when`(this.itemView.titleTextView).thenAnswer(Answer { invocation -> this.titleTextView })
        Mockito.`when`(this.itemView.priceTextView).thenAnswer(Answer { invocation -> this.titleTextView })
        Mockito.`when`(this.itemView.priceTextView).thenAnswer(Answer { invocation -> this.titleTextView })
        Mockito.`when`(this.itemView.context).thenAnswer(Answer { invocation -> this.context})
        Mockito.`when`(this.context.getString(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenAnswer( Answer { invocation ->
            val args = invocation.arguments
            return@Answer "test" + args[1] as String
        })
        basketHolder.bindData(Burger("test", "test", "test", null, 20, false))
        Assert.assertEquals("test0,2", this.itemView.titleTextView.text)
    }
}