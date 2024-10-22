package leading.moomin.expert

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.schibsted.spt.data.jslt.Parser
import kotlin.system.exitProcess
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters
import java.nio.file.Path
import java.util.concurrent.Callable
import kotlin.io.path.readText

@Command(
    name = "jslt",
    version = ["0.2.0"],
    mixinStandardHelpOptions = true
)
class JsltCommand: Callable<Int> {
    private val objectMapper = jacksonObjectMapper()

    @Parameters(index = "0", arity = "0..1", description = ["JSLT transform file"])
    private lateinit var jsltTransform: Path

    @Parameters(index = "1", arity = "0..1", description = ["JSON input to be transformed"])
    private lateinit var inputFile: Path

    @Option(
        names = ["-p", "--pretty"],
        description = ["Pretty print output"],
    )
    private var prettyPrint: Boolean = false

    override fun call(): Int =
        try {
            requireNotNull(jsltTransform) { "No jslt file given in parameters" }
            requireNotNull(inputFile) { "No json input given found in parameters" }

            val input: JsonNode = objectMapper.readTree(inputFile.readText())

            val jslt = Parser.compile(jsltTransform.toFile())

            val output = jslt.apply(input)

            if (prettyPrint) {
                println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output))
            } else {
                println(output)
            }

            0
        } catch (exc: Exception) {
            println(exc.message)
            1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
           exitProcess(CommandLine(JsltCommand()).execute(*args))
        }
    }
}
