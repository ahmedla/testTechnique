package fr.bigburger.global

import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer

class UtilsTest {

    @Mock
    lateinit var context: Context

    @Before
    fun setUp () {
      MockitoAnnotations.initMocks(this)
    }

    @Test
    fun handlePriceTest(){
        Mockito.`when`(this.context.getString(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenAnswer( Answer { invocation ->
            val args = invocation.arguments
           return@Answer "test:" + args[1] as String
        })

        Assert.assertEquals("test:0,1", Utils.handlePrice(this.context, 10))
        Assert.assertEquals("test:0,5", Utils.handlePrice(this.context, 50))
        Assert.assertEquals("test:7", Utils.handlePrice(this.context, 700))
    }
}