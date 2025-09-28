package knob

import kotlin.script.experimental.api.ScriptEvaluationConfiguration
import kotlin.script.experimental.api.scriptsInstancesSharing

object KnobEvaluationConfiguration :
    ScriptEvaluationConfiguration({
        scriptsInstancesSharing(true)
    })
