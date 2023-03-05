package ru.iaie.reflexold.generator

//import static extension ru.iaie.reflexold.utils.ReflexOldModelUtil.*
//import static extension ru.iaie.reflexold.utils.LiteralUtil.*
import static extension ru.iaie.reflexold.utils.ExpressionUtil.*
import static extension ru.iaie.reflexold.utils.TransliterationUtil.*
//import static extension ru.iaie.reflexold.utils.TypeUtil.*
//import ru.iaie.reflexold.utils.TypeUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import ru.iaie.reflexold.reflexOld.InfixOp
import ru.iaie.reflexold.reflexOld.PostfixOp
import ru.iaie.reflexold.reflexOld.FunctionCall
import ru.iaie.reflexold.reflexOld.IdReference
import ru.iaie.reflexold.reflexOld.PrimaryExpression
import ru.iaie.reflexold.reflexOld.UnaryExpression
import ru.iaie.reflexold.reflexOld.CastExpression
import ru.iaie.reflexold.reflexOld.MultiplicativeExpression
import ru.iaie.reflexold.reflexOld.AdditiveExpression
import ru.iaie.reflexold.reflexOld.CheckStateExpression
import ru.iaie.reflexold.reflexOld.ShiftExpression
import ru.iaie.reflexold.reflexOld.CompareExpression
import ru.iaie.reflexold.reflexOld.EqualityExpression
import ru.iaie.reflexold.reflexOld.BitAndExpression
import ru.iaie.reflexold.reflexOld.BitXorExpression
import ru.iaie.reflexold.reflexOld.BitOrExpression
import ru.iaie.reflexold.reflexOld.LogicalAndExpression
import ru.iaie.reflexold.reflexOld.LogicalOrExpression
import ru.iaie.reflexold.reflexOld.AssignmentExpression
import ru.iaie.reflexold.reflexOld.PhysicalVariable
import ru.iaie.reflexold.reflexOld.ProgramVariable
//import ru.iaie.reflexold.reflexOld.Process
import ru.iaie.reflexold.reflexOld.Const
import ru.iaie.reflexold.reflexOld.EnumMember
//import ru.iaie.reflexold.reflexOld.ProcessVariable
import ru.iaie.reflexold.reflexOld.Type
import java.util.Set
import java.util.Map
import ru.iaie.reflexold.reflexOld.ProcessVariable
import ru.iaie.reflexold.reflexOld.CompareEqOp
import ru.iaie.reflexold.reflexOld.MultiplicativeOp
import ru.iaie.reflexold.reflexOld.UnaryOp
import ru.iaie.reflexold.reflexOld.InfixPostfixOp

class ExpressionGenerator {
	
	protected Set<Type> UNSIGNED_TYPES
	protected Set<Type> INT_TYPES
	protected Set<Type> FLOAT_TYPES
	protected Map<Type, Integer> TYPE_ORDER
	protected Map<Type, Integer> INT_TYPE_SIZES
	
	protected boolean isEnglish
	
	new (boolean isEnglish) {
		this.isEnglish = isEnglish
		UNSIGNED_TYPES = newHashSet(Type.USHORT_EN, Type.USHORT_RU, Type.UINT_EN, Type.UINT_RU, Type.ULONG_EN, Type.ULONG_RU)
		INT_TYPES = newHashSet(Type.SHORT_EN, Type.SHORT_RU, Type.INT_EN, Type.INT_RU, Type.LONG_EN, Type.LONG_RU)
		FLOAT_TYPES = newHashSet(Type.FLOAT_EN, Type.FLOAT_RU, Type.DOUBLE_EN, Type.DOUBLE_RU)
		TYPE_ORDER = newHashMap(
			Type.BOOL_RU	-> 0,
			Type.BOOL_EN	-> 0,
			Type.SHORT_RU	-> 1,
			Type.SHORT_EN	-> 1,
			Type.USHORT_RU	-> 2,
			Type.USHORT_EN	-> 2,
			Type.INT_RU		-> 3,
			Type.INT_EN		-> 3,
			Type.UINT_RU	-> 4,
			Type.UINT_EN	-> 4,
			Type.LONG_RU	-> 5,
			Type.LONG_EN	-> 5,
			Type.ULONG_RU	-> 6,
			Type.ULONG_EN	-> 6,
			Type.FLOAT_RU	-> 7,
			Type.FLOAT_EN	-> 7,
			Type.DOUBLE_RU	-> 8,
			Type.DOUBLE_EN	-> 8
		)
		INT_TYPE_SIZES = newHashMap(
			Type.SHORT_EN	-> 8,
			Type.SHORT_RU	-> 8,
			Type.USHORT_EN	-> 8,
			Type.USHORT_RU	-> 8,
			Type.INT_EN		-> 16,
			Type.INT_RU		-> 16,
			Type.UINT_EN	-> 16,
			Type.UINT_RU	-> 16,
			Type.LONG_EN	-> 32,
			Type.LONG_RU	-> 32,
			Type.ULONG_EN	-> 32,
			Type.ULONG_RU	-> 32
		)
	}
	
	def boolean isUnsigned(Type type) {
		return UNSIGNED_TYPES.contains(type)
	}

	def boolean isIntType(Type type) {
		return INT_TYPES.contains(type)
	}
	
	def boolean isFloatType(Type type) {
		return FLOAT_TYPES.contains(type)
	}
	
	def boolean isBoolType(Type type) {
		return (type == Type.BOOL_EN || type == Type.BOOL_RU)
	}
	
	def int getSize(Type type) {
		if (isIntType(type)) {
			return INT_TYPE_SIZES.get(type)
		}
		throw new IllegalArgumentException('''Type size is undefined for «type»''');
	}

	def boolean isSigned(Type type) {
		return !isUnsigned(type)
	}
	
	def Type max(Type t1, Type t2) { //(old)TODO: stack overflow exception
		if (t1.isIntType && t2.isIntType) {
			if (t1.size == t2.size) {
				if (t1.signed) return t1;
				if (t2.signed) return t2;
			}
		}
		return (TYPE_ORDER.get(t1) >= TYPE_ORDER.get(t2)) ? t1 : t2;
	}
	
	def isCompitableInArithmeticExpression(Type t1, Type t2) {
		if (t1.isIntType && t2.isIntType) return true;
		if (t1.isFloatType && t2.isFloatType) return true;
		if (t1.isBoolType && t2.isBoolType) return true;
	}
		
	def isCompitableInLogicalExpression(Type t1, Type t2) {
		return true;
	}
	
	//enum OperationType {
	//	ARITHMETIC, LOGICAL, BIT, COMPARE, EQ
	//}
	
	
	
	
	def toID(String name) {
		if (isEnglish) return name
		else return name.transliterate
	}
	
	def getMapping(ProcessVariable pv) {
		switch (pv) {
			PhysicalVariable:
				return '''«pv.name.toID»'''
			ProgramVariable:
				return '''«pv.name.toID»'''
		}
	}
	
	def getMapping(IdReference ref) {
		switch (ref) {
			PhysicalVariable:
				return '''«ref.name.toID»'''
			ProgramVariable:
				return '''«ref.name.toID»'''
			Const:
				return '''«ref.name.toID»'''
			EnumMember:
				return '''«ref.name.toID»'''
		}
	}
	
	def String translateBoolLiteral(Boolean l) {
		return l.booleanValue ? "TRUE" : "FALSE"
	}
	
	def String translateType(Type t) {
		switch (t) {
			case SHORT_EN: return "SINT"
			case SHORT_RU: return "SINT"
			case USHORT_EN: return "USINT"
			case USHORT_RU: return "USINT"
			case INT_EN: return "INT"
			case INT_RU: return "INT"
			case UINT_EN: return "UINT"
			case UINT_RU: return "UINT"
			case LONG_EN: return "LINT"
			case LONG_RU: return "LINT"
			case ULONG_EN: return "ULINT"
			case ULONG_RU: return "ULINT"
			case FLOAT_EN: return "REAL"
			case FLOAT_RU: return "REAL"
			case DOUBLE_EN: return "LREAL"
			case DOUBLE_RU: return "LREAL"
			case BOOL_EN: return "BOOL"
			case BOOL_RU: return "BOOL"
			case VOID_EN: return ""
			case VOID_RU: return ""
		}
	}
	
	def translateCheckStateExpression(CheckStateExpression cse) {
		var s1 = cse.logicalNot? "NOT (" : ""
		var s2 = cse.logicalNot? ")" : ""
		switch (cse.qualfier) {
			case STOP_EN:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE STOP«s2»'''
			case STOP_RU:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE STOP«s2»'''
			case ERROR_EN:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE ERROR«s2»'''
			case ERROR_RU:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE ERROR«s2»'''
			case ACTIVE_EN:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE ACTIVE«s2»'''
			case ACTIVE_RU:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE ACTIVE«s2»'''
			case PASSIVE_EN:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE INACTIVE«s2»'''
			case PASSIVE_RU:
				return '''«s1»PROCESS «cse.process.name.toID» IN STATE INACTIVE«s2»'''
		}
	}
	
	def translateUnaryOp(UnaryOp op) {
		switch op {
			case PLUS: return ""
			case MINUS: return "-"
			case BIT_NOT: return "NOT "
			case LOGICAL_NOT: return "NOT "
		}
	}
	
	def String translateExpr(EObject expr) {   // TODO: CHECK
		switch (expr) {
			InfixOp:
				return '''«getMapping(expr.ref)» := «getMapping(expr.ref)» «IF expr.op == InfixPostfixOp.INC»+«ELSE»-«ENDIF» 1'''
				//return '''«expr.op» «getMapping(expr.ref)»'''
			PostfixOp:
				return '''«getMapping(expr.ref)» := «getMapping(expr.ref)» «IF expr.op == InfixPostfixOp.INC»+«ELSE»-«ENDIF» 1'''
				//return '''«getMapping(expr.ref)» «expr.op»'''
			FunctionCall: {
				var s1 = expr.args.empty? "" : "("
				var s2 = expr.args.empty? "" : ")"
				return '''«s1»_FUNCTION_«FOR arg : expr.args» + «translateExpr(arg)»«ENDFOR»«s2»'''
			}
			IdReference:
				return '''«getMapping(expr)»'''
			PrimaryExpression: {
				if (expr.isNestedExpr) {
					return '''(«translateExpr(expr.nestedExpr)»)'''
				}
				if (expr.isBoolean) {
					return translateBoolLiteral(expr.bool)
				}
				if (expr.isReference) {
					return translateExpr(expr.reference)
				}
				
				var String str = NodeModelUtils.getNode(expr).text.trim.replaceAll("[+&lLuU]", "")  // .replaceAll("[+-lLuU]", "")
				if (expr.isInteger) {
					if (str.contains("0x") || str.contains("0X")) {
						return str.replace("0x", "16#").replace("0X", "16#")
					}
					if ((str.startsWith("0") && str.length > 1) || (str.startsWith("-0") && str.length > 2)) {
						return str.replaceFirst("0", "8#")
					}
				}
				return str
			}
			UnaryExpression:
				return '''«translateUnaryOp(expr.unaryOp)»«translateExpr(expr.right)»'''
				//return '''«IF !expr.referenceOp»«translateUnaryOp(expr.unaryOp)»«ENDIF»«translateExpr(expr.right)»'''
			CastExpression:
				return '''«translateExpr(expr.right)»'''
				//return '''(«translateType(expr.type)») «translateExpr(expr.right)»'''
			MultiplicativeExpression:
				return '''«translateExpr(expr.left)» «IF expr.mulOp == MultiplicativeOp.MOD»MOD«ELSE»«expr.mulOp»«ENDIF» «translateExpr(expr.right)»'''
			AdditiveExpression:
				return '''«translateExpr(expr.left)» «expr.addOp» «translateExpr(expr.right)»'''
			CheckStateExpression:
				return translateCheckStateExpression(expr)
			//ShiftExpression:
			//	return '''«translateExpr(expr.left)» «expr.shiftOp» «translateExpr(expr.right)»'''
			CompareExpression:
				return '''«translateExpr(expr.left)» «expr.cmpOp» «translateExpr(expr.right)»'''
			EqualityExpression:
				return '''«translateExpr(expr.left)» «IF expr.eqCmpOp == CompareEqOp.NOT_EQ»<>«ELSE»=«ENDIF» «translateExpr(expr.right)»'''
			BitAndExpression:
				return '''«translateExpr(expr.left)» AND «translateExpr(expr.right)»'''
			BitXorExpression:
				return '''«translateExpr(expr.left)» XOR «translateExpr(expr.right)»'''
			BitOrExpression:
				return '''«translateExpr(expr.left)» OR «translateExpr(expr.right)»'''
			LogicalAndExpression:
				return '''«translateExpr(expr.left)» AND «translateExpr(expr.right)»'''
			LogicalOrExpression:
				return '''«translateExpr(expr.left)» OR «translateExpr(expr.right)»'''
			AssignmentExpression: {
				if (expr.hasAssignment)
					return translateAssignmentExpression(expr)
					//return '''«translateExpr(expr.assignVar)» «expr.assignOp» «translateExpr(expr.expr)»'''
				return '''«translateExpr(expr.expr)»'''
			}
		}
	}
	
	// ASSIGN="=" | MUL="*=" | DIV="/=" | MOD="+=" | SUB="-=" | CIN="<<=" |
	// COUT=">>=" | BIT_AND="&=" | BIT_XOR="^=" | BIT_OR="|=";
	def translateAssignmentExpression(AssignmentExpression ae) {
		switch ae.assignOp {
			case ASSIGN:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.expr)»'''
			case MUL:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» * «translateExpr(ae.expr)»'''
			case DIV:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» / «translateExpr(ae.expr)»'''
			case MOD:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» + «translateExpr(ae.expr)»'''
			case SUB:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» - «translateExpr(ae.expr)»'''
			case BIT_AND:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» AND «translateExpr(ae.expr)»'''
			case BIT_OR:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» OR «translateExpr(ae.expr)»'''
			case BIT_XOR:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)» XOR «translateExpr(ae.expr)»'''
			default:
				return '''«translateExpr(ae.assignVar)» := «translateExpr(ae.assignVar)»'''
		}
	}
	
	def Type resolveIdReferenceType(IdReference ref) {
		switch ref {
			PhysicalVariable:
				return ref.type
			ProgramVariable:
				return ref.type
			EnumMember:
				return resolveExprType(ref.value)
			Const:
				return resolveExprType(ref.value)
		}
	}
	
	def Type resolveExprType(EObject expr) {
		switch (expr) {
			InfixOp:
				return resolveExprType(expr.ref)
			PostfixOp:
				return resolveExprType(expr.ref)
			FunctionCall:
				return expr.function.returnType
			IdReference: {
				return resolveIdReferenceType(expr)
				//return resolveExprType(expr)
			}
			PrimaryExpression: {
				if (expr.isNestedExpr) {
					return resolveExprType(expr.nestedExpr)
				}
				if (expr.isBoolean) {
					return Type.BOOL_EN
				}
				if (expr.isReference) {
					return resolveExprType(expr.reference)
				}
				if (expr.isInteger) {
					return Type.LONG_EN
				}
				if (expr.isFloating) {
					return Type.DOUBLE_EN
				}
			}
			UnaryExpression:
				return resolveExprType(expr.right)
			CastExpression:
				return expr.type
			CheckStateExpression:
				return Type.BOOL_EN
			AssignmentExpression: {
				if (expr.hasAssignment) {
					return resolveExprType(expr.assignVar)
				}
				return resolveExprType(expr.expr)
			}
			default:
				resolveBinaryExprType(expr)
		}
	}

	def Type resolveBinaryExprType(EObject expr) {
		var EObject left
		var EObject right
		switch (expr) {
			MultiplicativeExpression: {
				left = expr.left
				right = expr.right
			}
			AdditiveExpression: {
				left = expr.left
				right = expr.right
			}
			ShiftExpression: {
				left = expr.left
				right = expr.right
			}
			CompareExpression: {
				left = expr.left
				right = expr.right
			}
			EqualityExpression: {
				left = expr.left
				right = expr.right
			}
			BitAndExpression: {
				left = expr.left
				right = expr.right
			}
			BitXorExpression: {
				left = expr.left
				right = expr.right
			}
			BitOrExpression: {
				left = expr.left
				right = expr.right
			}
			LogicalAndExpression: {
				left = expr.left
				right = expr.right
			}
			LogicalOrExpression: {
				left = expr.left
				right = expr.right
			}
		}
		val leftType = resolveExprType(left)
		val rightType = resolveExprType(right)
		return max(leftType, rightType)
	}
	
	
	
}