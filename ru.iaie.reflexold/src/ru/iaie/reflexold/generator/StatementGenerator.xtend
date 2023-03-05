package ru.iaie.reflexold.generator

import org.eclipse.emf.ecore.EObject
import static extension ru.iaie.reflexold.utils.ReflexOldModelUtil.*
import static extension ru.iaie.reflexold.utils.ExpressionUtil.*
//import ru.iaie.reflexold.reflexOld.Process
import ru.iaie.reflexold.reflexOld.State
import ru.iaie.reflexold.reflexOld.TimeoutFunction
import ru.iaie.reflexold.reflexOld.StopProcStat
import ru.iaie.reflexold.reflexOld.SetStateStat
import ru.iaie.reflexold.reflexOld.IfElseStat
import ru.iaie.reflexold.reflexOld.Expression
import ru.iaie.reflexold.reflexOld.SwitchStat
import ru.iaie.reflexold.reflexOld.StartProcStat
import ru.iaie.reflexold.reflexOld.ResetStat
import ru.iaie.reflexold.reflexOld.RestartStat
import ru.iaie.reflexold.reflexOld.CompoundStatement
import ru.iaie.reflexold.utils.LiteralUtil
import ru.iaie.reflexold.reflexOld.LoopStat
import ru.iaie.reflexold.reflexOld.ErrorStat
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import ru.iaie.reflexold.reflexOld.FunctionCall
import ru.iaie.reflexold.reflexOld.AssignmentExpression

class StatementGenerator {
	protected ExpressionGenerator expressionGenerator
	
	new(ExpressionGenerator expressionGenerator) {
		this.expressionGenerator = expressionGenerator
	}
	
	def translateName(String name) {
		expressionGenerator.toID(name)
	}
	
	def translateTimeoutFunction(State state, TimeoutFunction func) {
		return '''
		TIMEOUT «translateTimeout(func)» THEN
			«translateStatement(state, func.body)»
		END_TIMEOUT
		'''
	}

	def translateTimeout(TimeoutFunction func) {
		if (func.isClearTimeout)
			return LiteralUtil.parseInteger(func.time)
		if (func.isReferencedTimeout)
			expressionGenerator.getMapping(func.ref);
	}

	def String translateStatement(State state, EObject statement) {
		switch statement {
			StopProcStat:
				return translateStopProcStat(statement)
			ErrorStat:
				return translateErrorProcStat(statement)
			SetStateStat:
				return translateSetStateStat(state, statement)
			IfElseStat:
				return translateIfElseStat(state, statement)
			Expression:  //TODO: function fix _VOID_ := _FUNCTION_;
				return '''«IF statement instanceof AssignmentExpression && !(statement as AssignmentExpression).hasAssignment && (statement as AssignmentExpression).expr instanceof FunctionCall»_VOID_ := «ENDIF»«expressionGenerator.translateExpr(statement)»;'''
				//return '''«IF ((statement as Expression) instanceof UnaryExpression) && (((statement as Expression) as UnaryExpression) instanceof FunctionCall)»_VOID_ := «ENDIF»«expressionGenerator.translateExpr(statement)»;'''
			SwitchStat:
				return translateSwitchCaseStat(state, statement)
			StartProcStat:
				return translateStartProcStat(statement)
			ResetStat:
				return translateResetTimer()
			RestartStat:
				return translateRestartProcStat()
			CompoundStatement:
				return '''
				«FOR stat : statement.statements»
				«translateStatement(state, stat)»
				«ENDFOR»
				'''
		}
	}
	
	def translateIfElseStat(State state, IfElseStat stat) {  // «IF NodeModelUtils.getNode((stat.then as CompoundStatement).statements.get(0)).text.trim == ";"»	_VOID_ := _VOID_;
		return '''
		IF («expressionGenerator.translateExpr(stat.cond)») THEN
		«IF (stat.then instanceof CompoundStatement) && ((stat.then as CompoundStatement).isEmpty || NodeModelUtils.getNode((stat.then as CompoundStatement).statements.get(0)).text.trim == ";")»	_VOID_ := _VOID_;«ENDIF»
			«translateStatement(state, stat.then)»
		«IF stat.getElse !== null»	
		ELSE
			«translateStatement(state, stat.getElse)»
		«ENDIF»
		END_IF
		'''
	}

	def translateSwitchCaseStat(State state, SwitchStat stat) {  // TODO: empty cases and only break; cases
		var countEmptyOptionsAtEnd = 0
		var optionsLength = stat.options.length
		//var newOptions = stat.options
		for (variant : stat.options) {
			if ((variant.statements.empty && !variant.hasBreak) || (variant.statements.length == 1 && NodeModelUtils.getNode(variant.statements.get(0)).text.trim == ";" && !variant.hasBreak))
				countEmptyOptionsAtEnd += 1
			else
				countEmptyOptionsAtEnd = 0
		}
		var newOptions = stat.options.subList(0, optionsLength - countEmptyOptionsAtEnd)
		
		return '''
		CASE («expressionGenerator.translateExpr(stat.expr)») OF
			«FOR variant : newOptions»
			«IF (variant.statements.empty && !variant.hasBreak) || (variant.statements.length == 1 && NodeModelUtils.getNode(variant.statements.get(0)).text.trim == ";" && !variant.hasBreak)»
			«expressionGenerator.translateExpr(variant.option)»,
			«ELSE»
			«expressionGenerator.translateExpr(variant.option)»:
				«FOR statement: variant.statements» 
				«IF (statement instanceof CompoundStatement) && ((statement as CompoundStatement).isEmpty || NodeModelUtils.getNode((statement as CompoundStatement).statements.get(0)).text.trim == ";")»_VOID_ := _VOID_;«ENDIF»
				«translateStatement(state, statement)»
				«ENDFOR»
				«IF variant.hasBreak»_BREAK_ := _BREAK_;«ENDIF»
			«ENDIF»
			«ENDFOR»
			«IF stat.hasDefaultOption»ELSE
				«FOR statement: stat.defaultOption.statements» 
				«translateStatement(state, statement)»
				«ENDFOR»
				«IF stat.defaultOption.hasBreak»_BREAK_ := _BREAK_;«ENDIF»
			«ENDIF»
		END_CASE
		'''
	}
	/*
	def translateSwitchCaseStat(State state, SwitchStat stat) {  // TODO: empty cases and only break; cases
		return '''
		CASE («expressionGenerator.translateExpr(stat.expr)») OF
			«FOR variant : stat.options»
			«expressionGenerator.translateExpr(variant.option)»:
				«FOR statement: variant.statements» 
				«translateStatement(state, statement)»
				«ENDFOR»
				«IF variant.hasBreak»break;«ENDIF»
			«ENDFOR»
			«IF stat.hasDefaultOption»ELSE
				«FOR statement: stat.defaultOption.statements» 
				«translateStatement(state, statement)»
				«ENDFOR»
				«IF stat.defaultOption.hasBreak»break;«ENDIF»
			«ENDIF»
		END_CASE
		'''
	}*/

	def translateResetTimer() {
		return '''RESET TIMER;
		'''
	}

	def translateSetStateStat(State state, SetStateStat sss) {
		if (sss.isNext) {
			return '''SET NEXT;
			'''
		}
		return '''SET STATE «translateName(sss.state.name)»;
		'''
	}

	def translateStopProcStat(StopProcStat sps) {
		if (sps.selfStop) {
			return '''STOP;
			'''
		}
		return '''STOP PROCESS «translateName(sps.process.name)»;
		'''
	}
	
	def translateErrorProcStat(ErrorStat es) {
		if (es.selfError) {
			return '''ERROR;
			'''
		}
		return '''ERROR PROCESS «translateName(es.process.name)»;
		'''
	}

	def translateStartProcStat(StartProcStat sps) {
		return '''START PROCESS «translateName(sps.process.name)»;
		'''
	}

	def translateRestartProcStat() {
		return '''RESTART;
		'''
	}
	
	def boolean isStateLooped(State state) {
		for (statement : state.stateFunction.statements) {
			if (statement.hasLoop)
				return true
		}
		return false
	}
	
	def boolean hasLoop(EObject statement) {
		switch statement {
			LoopStat:
				return true
			IfElseStat: {
				if (hasLoop(statement.then))
					return true
				if (statement.getElse !== null)
					return hasLoop(statement.getElse)
				return false
			}
			SwitchStat: {
				for (variant : statement.options) {
					for (stat : variant.statements) {
						if (hasLoop(stat))
							return true
					}
				}
				if (statement.hasDefaultOption) {
					for (stat : statement.defaultOption.statements) {
						if (hasLoop(stat))
							return true
					}
				}
				return false
			}
			CompoundStatement: {
				for (stat : statement.statements) {
					if (hasLoop(stat))
						return true
				}
				return false
			}
			default:
				return false
		}
	}
	
	
}