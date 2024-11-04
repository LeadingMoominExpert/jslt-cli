package leading.moomin.expert

import picocli.CommandLine
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertIs

class JsltCommandTest {
    @Test
    fun help() {
        val sw = StringWriter()
        val cmd = CommandLine(JsltCommand()).apply {
            out = PrintWriter(sw)
        }

        val exitCode = cmd.execute("-h")

        assertEquals(exitCode, 0)
        assertContains(sw.toString(), "Usage: jslt")
    }

    @Test
    fun version() {
        val sw = StringWriter()
        val cmd = CommandLine(JsltCommand()).apply {
            out = PrintWriter(sw)
        }

        val exitCode = cmd.execute("-V")
        println(sw.toString())

        assertEquals(exitCode, 0)
        assertContains(sw.toString(), "0.2.0")
    }
}