package ru.iaie.reflexold.generator

//import static extension ru.iaie.reflexold.utils.ExpressionUtil.*
import static extension ru.iaie.reflexold.utils.ReflexOldModelUtil.*
import ru.iaie.reflexold.reflexOld.Const
import ru.iaie.reflexold.reflexOld.Enum
import ru.iaie.reflexold.reflexOld.Function
import ru.iaie.reflexold.reflexOld.Type
import ru.iaie.reflexold.reflexOld.Port
import ru.iaie.reflexold.reflexOld.Process
import ru.iaie.reflexold.reflexOld.ProcessVariable
import ru.iaie.reflexold.reflexOld.PhysicalVariable
import ru.iaie.reflexold.reflexOld.State
import ru.iaie.reflexold.reflexOld.EnumMember

class GeneratorHelper {
	
	protected ExpressionGenerator expressionGenerator
	protected StatementGenerator statementGenerator
	protected int enumCount
	
	new (boolean isEnglish) {
		this.expressionGenerator = new ExpressionGenerator(isEnglish)
		this.statementGenerator = new StatementGenerator(expressionGenerator)
		enumCount = 0
	}
	
	def translateName(String name) {
		expressionGenerator.toID(name)
	}
	
	def translateArgType(Type t) {
		expressionGenerator.translateType(t)
	}
	
	def translateProcVarName(ProcessVariable pv) {
		expressionGenerator.getMapping(pv)
	}
	
	def translatePortMapping(PhysicalVariable physVar) {
		return '''�translateName(physVar.mapping.port.name)�[�IF physVar.mapping.isSingleBitMapping�0�ENDIF�]'''
	}
	
	def generateConstantDefinition(Const c) {
		val constType = expressionGenerator.resolveExprType(c.value)
		return '''�expressionGenerator.getMapping(c)� : �expressionGenerator.translateType(constType)� := �expressionGenerator.translateExpr(c.value)�;
		'''
	}
	
	/*
	def generateEnumDefinition(Enum en) {
		var result = '''
		enum enumerator_�enumCount� {
			�FOR enumMember : en.enumMembers�
			�expressionGenerator.getMapping(enumMember)��IF enumMember.hasValue� = �expressionGenerator.translateExpr(enumMember.value)��ENDIF��IF isEnumMemberLast(enumMember, en)��ELSE�,�ENDIF�
			�ENDFOR�
		}
		'''
		enumCount++
		return result
	}
	*/
	
	//�var curName = expressionGenerator.getMapping(enumMember)�
	
	def generateEnumVarDefinition(Enum en) {
		var result = '\n'
		var prevName = ''
		for (enumMember : en.enumMembers) {
			result += generateEnumMemberVarDefinition(enumMember, prevName)
			prevName = expressionGenerator.getMapping(enumMember)
		}
		return result
	}
	
	def generateEnumMemberVarDefinition(EnumMember em, String prevName) {
		return '''
		�expressionGenerator.getMapping(em)� : INT := �IF em.hasValue��expressionGenerator.translateExpr(em.value)��ELSEIF prevName.isEmpty�0�ELSE��prevName� + 1�ENDIF�;
		'''
	}
	
	def generateFunctionDefinition(Function f) {
		return '''�expressionGenerator.translateType(f.returnType)� �f.name�(�String.join(", ", f.argTypes.map[translateArgType])�);
		'''
	}
	
	def generatePortDefinition(Port p) {
		switch p.type {
			case INPUT_EN: return '''input �translateName(p.name)� �p.addr1� �p.addr2� �p.size�;
			'''
			case INPUT_RU: return '''input �translateName(p.name)� �p.addr1� �p.addr2� �p.size�;
			'''
			case OUTPUT_EN: return '''output �translateName(p.name)� �p.addr1� �p.addr2� �p.size�;
			'''
			case OUTPUT_RU: '''output �translateName(p.name)� �p.addr1� �p.addr2� �p.size�;
			'''
		}
	}
	
	/*
	Process:
		("PROC" | "����") name=ID "{"
		((imports+=ImportedVariableList | variables+=ProcessVariable) ";")*
		states+=State*
		"}";
	
	ImportedVariableList:
		(("FROM" "PROC") | ("��" "����")) process=[Process]
		variables+=[ProcessVariable] ("," variables+=[ProcessVariable])*;
	
	ProcessVariable:
		(PhysicalVariable | ProgramVariable)
		(
			(local?=("LOCAL" | "�����")) |
			(shared?=("FOR" | "���")
				(
					("ALL" | "����") |
					((("PROC") | ("����")) processes+=[Process] ("," processes+=[Process])*)
				)
			)
		);
	*/
	def generateProcessDefinition(Process p) {
		var needVarBlock = false
		for (pv : p.variables) {
			if (!pv.isShared)
				needVarBlock = true
		}
		
		return '''
		PROCESS �translateName(p.name)�
			�IF needVarBlock�
			VAR
				�FOR procVar : p.variables�
				�IF !procVar.isShared�
				�generateProcVarDefinition(procVar)�
				�ENDIF�
				�ENDFOR�
			END_VAR
			�ENDIF�
			�FOR state : p.states�
			
			�generateStateDefinition(state)�
			�ENDFOR�
		END_PROCESS
		
		'''
	}
	
	/*
	ProgramVariable:
		type=Type name=ID;
	PhysicalVariable:
		type=Type name=ID "=" mapping=PortMapping;
	PortMapping:
		"{" port=[Port] "[" (bits=INTEGER) "]" "}";
	Port:
		type=PortType name=ID addr1=INTEGER addr2=INTEGER size=INTEGER ";";
	*/
	def generateProcVarDefinition(ProcessVariable pv) {
		return '''�translateName(pv.name)� : �expressionGenerator.translateType(pv.type)�;
		'''
	}
	
	def generateStateDefinition(State state) {
		return '''
		STATE �translateName(state.name)� �IF statementGenerator.isStateLooped(state)�LOOPED �ENDIF�
			�FOR stat : state.stateFunction.statements�
			�statementGenerator.translateStatement(state, stat)�
			�ENDFOR�
			�IF state.timeoutFunction !== null �
			
			�statementGenerator.translateTimeoutFunction(state, state.timeoutFunction)�
			�ENDIF�
		END_STATE
		'''
	}
	
}