package leading.moomin.expert

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.schibsted.spt.data.jslt.Parser
import java.util.logging.Logger
import kotlin.system.exitProcess
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.nio.file.Path
import java.util.concurrent.Callable
import kotlin.io.path.readText

@Command(
    name = "jslt",
    version = ["0.1.0"]
)
class JsltCommand: Callable<Int> {
    private val logger = Logger.getLogger(javaClass.name)
    private val objectMapper = jacksonObjectMapper()

    @Parameters(index = "0", arity = "0..1", description = ["JSLT transform file"])
    private val jsltTransform: Path? = null

    @Parameters(index = "1", arity = "0..1", description = ["JSON input to be transformed"])
    private val inputFile: Path? = null

    override fun call(): Int =
        try {
            requireNotNull(jsltTransform) { "No jslt file given in parameters" }
            requireNotNull(inputFile) { "No json input given found in parameters" }

            val input: JsonNode = objectMapper.readTree(inputFile.readText())

            val jslt = Parser.compile(jsltTransform.toFile())

            val output = jslt.apply(input)

            val pretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output)

            println(pretty)

            0
        } catch (exc: Exception) {
            logger.warning(exc.message)
            1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
           exitProcess(CommandLine(JsltCommand()).execute(*args))
        }
    }
}

