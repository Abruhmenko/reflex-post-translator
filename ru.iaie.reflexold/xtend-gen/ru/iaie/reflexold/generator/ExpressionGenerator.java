package ru.iaie.reflexold.generator;

import com.google.common.base.Objects;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import ru.iaie.reflexold.reflexOld.AdditiveExpression;
import ru.iaie.reflexold.reflexOld.AdditiveOp;
import ru.iaie.reflexold.reflexOld.AssignOperator;
import ru.iaie.reflexold.reflexOld.AssignmentExpression;
import ru.iaie.reflexold.reflexOld.BitAndExpression;
import ru.iaie.reflexold.reflexOld.BitOrExpression;
import ru.iaie.reflexold.reflexOld.BitXorExpression;
import ru.iaie.reflexold.reflexOld.CastExpression;
import ru.iaie.reflexold.reflexOld.CheckStateExpression;
import ru.iaie.reflexold.reflexOld.CompareEqOp;
import ru.iaie.reflexold.reflexOld.CompareExpression;
import ru.iaie.reflexold.reflexOld.CompareOp;
import ru.iaie.reflexold.reflexOld.Const;
import ru.iaie.reflexold.reflexOld.EnumMember;
import ru.iaie.reflexold.reflexOld.EqualityExpression;
import ru.iaie.reflexold.reflexOld.Expression;
import ru.iaie.reflexold.reflexOld.FunctionCall;
import ru.iaie.reflexold.reflexOld.IdReference;
import ru.iaie.reflexold.reflexOld.InfixOp;
import ru.iaie.reflexold.reflexOld.InfixPostfixOp;
import ru.iaie.reflexold.reflexOld.LogicalAndExpression;
import ru.iaie.reflexold.reflexOld.LogicalOrExpression;
import ru.iaie.reflexold.reflexOld.MultiplicativeExpression;
import ru.iaie.reflexold.reflexOld.MultiplicativeOp;
import ru.iaie.reflexold.reflexOld.PhysicalVariable;
import ru.iaie.reflexold.reflexOld.PostfixOp;
import ru.iaie.reflexold.reflexOld.PrimaryExpression;
import ru.iaie.reflexold.reflexOld.ProcessVariable;
import ru.iaie.reflexold.reflexOld.ProgramVariable;
import ru.iaie.reflexold.reflexOld.ShiftExpression;
import ru.iaie.reflexold.reflexOld.StateQualifier;
import ru.iaie.reflexold.reflexOld.Type;
import ru.iaie.reflexold.reflexOld.UnaryExpression;
import ru.iaie.reflexold.reflexOld.UnaryOp;
import ru.iaie.reflexold.utils.ExpressionUtil;
import ru.iaie.reflexold.utils.TransliterationUtil;

@SuppressWarnings("all")
public class ExpressionGenerator {
  protected Set<Type> UNSIGNED_TYPES;
  
  protected Set<Type> INT_TYPES;
  
  protected Set<Type> FLOAT_TYPES;
  
  protected Map<Type, Integer> TYPE_ORDER;
  
  protected Map<Type, Integer> INT_TYPE_SIZES;
  
  protected boolean isEnglish;
  
  public ExpressionGenerator(final boolean isEnglish) {
    this.isEnglish = isEnglish;
    this.UNSIGNED_TYPES = CollectionLiterals.<Type>newHashSet(Type.USHORT_EN, Type.USHORT_RU, Type.UINT_EN, Type.UINT_RU, Type.ULONG_EN, Type.ULONG_RU);
    this.INT_TYPES = CollectionLiterals.<Type>newHashSet(Type.SHORT_EN, Type.SHORT_RU, Type.INT_EN, Type.INT_RU, Type.LONG_EN, Type.LONG_RU);
    this.FLOAT_TYPES = CollectionLiterals.<Type>newHashSet(Type.FLOAT_EN, Type.FLOAT_RU, Type.DOUBLE_EN, Type.DOUBLE_RU);
    Pair<Type, Integer> _mappedTo = Pair.<Type, Integer>of(Type.BOOL_RU, Integer.valueOf(0));
    Pair<Type, Integer> _mappedTo_1 = Pair.<Type, Integer>of(Type.BOOL_EN, Integer.valueOf(0));
    Pair<Type, Integer> _mappedTo_2 = Pair.<Type, Integer>of(Type.SHORT_RU, Integer.valueOf(1));
    Pair<Type, Integer> _mappedTo_3 = Pair.<Type, Integer>of(Type.SHORT_EN, Integer.valueOf(1));
    Pair<Type, Integer> _mappedTo_4 = Pair.<Type, Integer>of(Type.USHORT_RU, Integer.valueOf(2));
    Pair<Type, Integer> _mappedTo_5 = Pair.<Type, Integer>of(Type.USHORT_EN, Integer.valueOf(2));
    Pair<Type, Integer> _mappedTo_6 = Pair.<Type, Integer>of(Type.INT_RU, Integer.valueOf(3));
    Pair<Type, Integer> _mappedTo_7 = Pair.<Type, Integer>of(Type.INT_EN, Integer.valueOf(3));
    Pair<Type, Integer> _mappedTo_8 = Pair.<Type, Integer>of(Type.UINT_RU, Integer.valueOf(4));
    Pair<Type, Integer> _mappedTo_9 = Pair.<Type, Integer>of(Type.UINT_EN, Integer.valueOf(4));
    Pair<Type, Integer> _mappedTo_10 = Pair.<Type, Integer>of(Type.LONG_RU, Integer.valueOf(5));
    Pair<Type, Integer> _mappedTo_11 = Pair.<Type, Integer>of(Type.LONG_EN, Integer.valueOf(5));
    Pair<Type, Integer> _mappedTo_12 = Pair.<Type, Integer>of(Type.ULONG_RU, Integer.valueOf(6));
    Pair<Type, Integer> _mappedTo_13 = Pair.<Type, Integer>of(Type.ULONG_EN, Integer.valueOf(6));
    Pair<Type, Integer> _mappedTo_14 = Pair.<Type, Integer>of(Type.FLOAT_RU, Integer.valueOf(7));
    Pair<Type, Integer> _mappedTo_15 = Pair.<Type, Integer>of(Type.FLOAT_EN, Integer.valueOf(7));
    Pair<Type, Integer> _mappedTo_16 = Pair.<Type, Integer>of(Type.DOUBLE_RU, Integer.valueOf(8));
    Pair<Type, Integer> _mappedTo_17 = Pair.<Type, Integer>of(Type.DOUBLE_EN, Integer.valueOf(8));
    this.TYPE_ORDER = CollectionLiterals.<Type, Integer>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16, _mappedTo_17);
    Pair<Type, Integer> _mappedTo_18 = Pair.<Type, Integer>of(Type.SHORT_EN, Integer.valueOf(8));
    Pair<Type, Integer> _mappedTo_19 = Pair.<Type, Integer>of(Type.SHORT_RU, Integer.valueOf(8));
    Pair<Type, Integer> _mappedTo_20 = Pair.<Type, Integer>of(Type.USHORT_EN, Integer.valueOf(8));
    Pair<Type, Integer> _mappedTo_21 = Pair.<Type, Integer>of(Type.USHORT_RU, Integer.valueOf(8));
    Pair<Type, Integer> _mappedTo_22 = Pair.<Type, Integer>of(Type.INT_EN, Integer.valueOf(16));
    Pair<Type, Integer> _mappedTo_23 = Pair.<Type, Integer>of(Type.INT_RU, Integer.valueOf(16));
    Pair<Type, Integer> _mappedTo_24 = Pair.<Type, Integer>of(Type.UINT_EN, Integer.valueOf(16));
    Pair<Type, Integer> _mappedTo_25 = Pair.<Type, Integer>of(Type.UINT_RU, Integer.valueOf(16));
    Pair<Type, Integer> _mappedTo_26 = Pair.<Type, Integer>of(Type.LONG_EN, Integer.valueOf(32));
    Pair<Type, Integer> _mappedTo_27 = Pair.<Type, Integer>of(Type.LONG_RU, Integer.valueOf(32));
    Pair<Type, Integer> _mappedTo_28 = Pair.<Type, Integer>of(Type.ULONG_EN, Integer.valueOf(32));
    Pair<Type, Integer> _mappedTo_29 = Pair.<Type, Integer>of(Type.ULONG_RU, Integer.valueOf(32));
    this.INT_TYPE_SIZES = CollectionLiterals.<Type, Integer>newHashMap(_mappedTo_18, _mappedTo_19, _mappedTo_20, _mappedTo_21, _mappedTo_22, _mappedTo_23, _mappedTo_24, _mappedTo_25, _mappedTo_26, _mappedTo_27, _mappedTo_28, _mappedTo_29);
  }
  
  public boolean isUnsigned(final Type type) {
    return this.UNSIGNED_TYPES.contains(type);
  }
  
  public boolean isIntType(final Type type) {
    return this.INT_TYPES.contains(type);
  }
  
  public boolean isFloatType(final Type type) {
    return this.FLOAT_TYPES.contains(type);
  }
  
  public boolean isBoolType(final Type type) {
    return (Objects.equal(type, Type.BOOL_EN) || Objects.equal(type, Type.BOOL_RU));
  }
  
  public int getSize(final Type type) {
    boolean _isIntType = this.isIntType(type);
    if (_isIntType) {
      return (this.INT_TYPE_SIZES.get(type)).intValue();
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Type size is undefined for ");
    _builder.append(type);
    throw new IllegalArgumentException(_builder.toString());
  }
  
  public boolean isSigned(final Type type) {
    boolean _isUnsigned = this.isUnsigned(type);
    return (!_isUnsigned);
  }
  
  public Type max(final Type t1, final Type t2) {
    if ((this.isIntType(t1) && this.isIntType(t2))) {
      int _size = this.getSize(t1);
      int _size_1 = this.getSize(t2);
      boolean _equals = (_size == _size_1);
      if (_equals) {
        boolean _isSigned = this.isSigned(t1);
        if (_isSigned) {
          return t1;
        }
        boolean _isSigned_1 = this.isSigned(t2);
        if (_isSigned_1) {
          return t2;
        }
      }
    }
    Type _xifexpression = null;
    Integer _get = this.TYPE_ORDER.get(t1);
    Integer _get_1 = this.TYPE_ORDER.get(t2);
    boolean _greaterEqualsThan = (_get.compareTo(_get_1) >= 0);
    if (_greaterEqualsThan) {
      _xifexpression = t1;
    } else {
      _xifexpression = t2;
    }
    return _xifexpression;
  }
  
  public boolean isCompitableInArithmeticExpression(final Type t1, final Type t2) {
    if ((this.isIntType(t1) && this.isIntType(t2))) {
      return true;
    }
    if ((this.isFloatType(t1) && this.isFloatType(t2))) {
      return true;
    }
    if ((this.isBoolType(t1) && this.isBoolType(t2))) {
      return true;
    }
    return false;
  }
  
  public boolean isCompitableInLogicalExpression(final Type t1, final Type t2) {
    return true;
  }
  
  public String toID(final String name) {
    if (this.isEnglish) {
      return name;
    } else {
      return TransliterationUtil.transliterate(name);
    }
  }
  
  public String getMapping(final ProcessVariable pv) {
    boolean _matched = false;
    if (pv instanceof PhysicalVariable) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      String _iD = this.toID(((PhysicalVariable)pv).getName());
      _builder.append(_iD);
      return _builder.toString();
    }
    if (!_matched) {
      if (pv instanceof ProgramVariable) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _iD = this.toID(((ProgramVariable)pv).getName());
        _builder.append(_iD);
        return _builder.toString();
      }
    }
    return null;
  }
  
  public String getMapping(final IdReference ref) {
    boolean _matched = false;
    if (ref instanceof PhysicalVariable) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      String _iD = this.toID(((PhysicalVariable)ref).getName());
      _builder.append(_iD);
      return _builder.toString();
    }
    if (!_matched) {
      if (ref instanceof ProgramVariable) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _iD = this.toID(((ProgramVariable)ref).getName());
        _builder.append(_iD);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (ref instanceof Const) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _iD = this.toID(((Const)ref).getName());
        _builder.append(_iD);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (ref instanceof EnumMember) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _iD = this.toID(((EnumMember)ref).getName());
        _builder.append(_iD);
        return _builder.toString();
      }
    }
    return null;
  }
  
  public String translateBoolLiteral(final Boolean l) {
    String _xifexpression = null;
    boolean _booleanValue = l.booleanValue();
    if (_booleanValue) {
      _xifexpression = "TRUE";
    } else {
      _xifexpression = "FALSE";
    }
    return _xifexpression;
  }
  
  public String translateType(final Type t) {
    if (t != null) {
      switch (t) {
        case SHORT_EN:
          return "SINT";
        case SHORT_RU:
          return "SINT";
        case USHORT_EN:
          return "USINT";
        case USHORT_RU:
          return "USINT";
        case INT_EN:
          return "INT";
        case INT_RU:
          return "INT";
        case UINT_EN:
          return "UINT";
        case UINT_RU:
          return "UINT";
        case LONG_EN:
          return "LINT";
        case LONG_RU:
          return "LINT";
        case ULONG_EN:
          return "ULINT";
        case ULONG_RU:
          return "ULINT";
        case FLOAT_EN:
          return "REAL";
        case FLOAT_RU:
          return "REAL";
        case DOUBLE_EN:
          return "LREAL";
        case DOUBLE_RU:
          return "LREAL";
        case BOOL_EN:
          return "BOOL";
        case BOOL_RU:
          return "BOOL";
        case VOID_EN:
          return "";
        case VOID_RU:
          return "";
        default:
          break;
      }
    }
    return null;
  }
  
  public String translateCheckStateExpression(final CheckStateExpression cse) {
    String _xifexpression = null;
    boolean _isLogicalNot = cse.isLogicalNot();
    if (_isLogicalNot) {
      _xifexpression = "NOT (";
    } else {
      _xifexpression = "";
    }
    String s1 = _xifexpression;
    String _xifexpression_1 = null;
    boolean _isLogicalNot_1 = cse.isLogicalNot();
    if (_isLogicalNot_1) {
      _xifexpression_1 = ")";
    } else {
      _xifexpression_1 = "";
    }
    String s2 = _xifexpression_1;
    StateQualifier _qualfier = cse.getQualfier();
    if (_qualfier != null) {
      switch (_qualfier) {
        case STOP_EN:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(s1);
          _builder.append("PROCESS ");
          String _iD = this.toID(cse.getProcess().getName());
          _builder.append(_iD);
          _builder.append(" IN STATE STOP");
          _builder.append(s2);
          return _builder.toString();
        case STOP_RU:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append(s1);
          _builder_1.append("PROCESS ");
          String _iD_1 = this.toID(cse.getProcess().getName());
          _builder_1.append(_iD_1);
          _builder_1.append(" IN STATE STOP");
          _builder_1.append(s2);
          return _builder_1.toString();
        case ERROR_EN:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(s1);
          _builder_2.append("PROCESS ");
          String _iD_2 = this.toID(cse.getProcess().getName());
          _builder_2.append(_iD_2);
          _builder_2.append(" IN STATE ERROR");
          _builder_2.append(s2);
          return _builder_2.toString();
        case ERROR_RU:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append(s1);
          _builder_3.append("PROCESS ");
          String _iD_3 = this.toID(cse.getProcess().getName());
          _builder_3.append(_iD_3);
          _builder_3.append(" IN STATE ERROR");
          _builder_3.append(s2);
          return _builder_3.toString();
        case ACTIVE_EN:
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append(s1);
          _builder_4.append("PROCESS ");
          String _iD_4 = this.toID(cse.getProcess().getName());
          _builder_4.append(_iD_4);
          _builder_4.append(" IN STATE ACTIVE");
          _builder_4.append(s2);
          return _builder_4.toString();
        case ACTIVE_RU:
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append(s1);
          _builder_5.append("PROCESS ");
          String _iD_5 = this.toID(cse.getProcess().getName());
          _builder_5.append(_iD_5);
          _builder_5.append(" IN STATE ACTIVE");
          _builder_5.append(s2);
          return _builder_5.toString();
        case PASSIVE_EN:
          StringConcatenation _builder_6 = new StringConcatenation();
          _builder_6.append(s1);
          _builder_6.append("PROCESS ");
          String _iD_6 = this.toID(cse.getProcess().getName());
          _builder_6.append(_iD_6);
          _builder_6.append(" IN STATE INACTIVE");
          _builder_6.append(s2);
          return _builder_6.toString();
        case PASSIVE_RU:
          StringConcatenation _builder_7 = new StringConcatenation();
          _builder_7.append(s1);
          _builder_7.append("PROCESS ");
          String _iD_7 = this.toID(cse.getProcess().getName());
          _builder_7.append(_iD_7);
          _builder_7.append(" IN STATE INACTIVE");
          _builder_7.append(s2);
          return _builder_7.toString();
        default:
          break;
      }
    }
    return null;
  }
  
  public String translateUnaryOp(final UnaryOp op) {
    if (op != null) {
      switch (op) {
        case PLUS:
          return "";
        case MINUS:
          return "-";
        case BIT_NOT:
          return "NOT ";
        case LOGICAL_NOT:
          return "NOT ";
        default:
          break;
      }
    }
    return null;
  }
  
  public String translateExpr(final EObject expr) {
    boolean _matched = false;
    if (expr instanceof InfixOp) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      String _mapping = this.getMapping(((InfixOp)expr).getRef());
      _builder.append(_mapping);
      _builder.append(" := ");
      String _mapping_1 = this.getMapping(((InfixOp)expr).getRef());
      _builder.append(_mapping_1);
      _builder.append(" ");
      {
        InfixPostfixOp _op = ((InfixOp)expr).getOp();
        boolean _equals = Objects.equal(_op, InfixPostfixOp.INC);
        if (_equals) {
          _builder.append("+");
        } else {
          _builder.append("-");
        }
      }
      _builder.append(" 1");
      return _builder.toString();
    }
    if (!_matched) {
      if (expr instanceof PostfixOp) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _mapping = this.getMapping(((PostfixOp)expr).getRef());
        _builder.append(_mapping);
        _builder.append(" := ");
        String _mapping_1 = this.getMapping(((PostfixOp)expr).getRef());
        _builder.append(_mapping_1);
        _builder.append(" ");
        {
          InfixPostfixOp _op = ((PostfixOp)expr).getOp();
          boolean _equals = Objects.equal(_op, InfixPostfixOp.INC);
          if (_equals) {
            _builder.append("+");
          } else {
            _builder.append("-");
          }
        }
        _builder.append(" 1");
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof FunctionCall) {
        _matched=true;
        String _xifexpression = null;
        boolean _isEmpty = ((FunctionCall)expr).getArgs().isEmpty();
        if (_isEmpty) {
          _xifexpression = "";
        } else {
          _xifexpression = "(";
        }
        String s1 = _xifexpression;
        String _xifexpression_1 = null;
        boolean _isEmpty_1 = ((FunctionCall)expr).getArgs().isEmpty();
        if (_isEmpty_1) {
          _xifexpression_1 = "";
        } else {
          _xifexpression_1 = ")";
        }
        String s2 = _xifexpression_1;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(s1);
        _builder.append("_FUNCTION_");
        {
          EList<Expression> _args = ((FunctionCall)expr).getArgs();
          for(final Expression arg : _args) {
            _builder.append(" + ");
            String _translateExpr = this.translateExpr(arg);
            _builder.append(_translateExpr);
          }
        }
        _builder.append(s2);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof IdReference) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _mapping = this.getMapping(((IdReference)expr));
        _builder.append(_mapping);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof PrimaryExpression) {
        _matched=true;
        boolean _isNestedExpr = ExpressionUtil.isNestedExpr(((PrimaryExpression)expr));
        if (_isNestedExpr) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("(");
          String _translateExpr = this.translateExpr(((PrimaryExpression)expr).getNestedExpr());
          _builder.append(_translateExpr);
          _builder.append(")");
          return _builder.toString();
        }
        boolean _isBoolean = ExpressionUtil.isBoolean(((PrimaryExpression)expr));
        if (_isBoolean) {
          return this.translateBoolLiteral(((PrimaryExpression)expr).getBool());
        }
        boolean _isReference = ExpressionUtil.isReference(((PrimaryExpression)expr));
        if (_isReference) {
          return this.translateExpr(((PrimaryExpression)expr).getReference());
        }
        String str = NodeModelUtils.getNode(expr).getText().trim().replaceAll("[+&lLuU]", "");
        boolean _isInteger = ExpressionUtil.isInteger(((PrimaryExpression)expr));
        if (_isInteger) {
          if ((str.contains("0x") || str.contains("0X"))) {
            return str.replace("0x", "16#").replace("0X", "16#");
          }
          if (((str.startsWith("0") && (str.length() > 1)) || (str.startsWith("-0") && (str.length() > 2)))) {
            return str.replaceFirst("0", "8#");
          }
        }
        return str;
      }
    }
    if (!_matched) {
      if (expr instanceof UnaryExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateUnaryOp = this.translateUnaryOp(((UnaryExpression)expr).getUnaryOp());
        _builder.append(_translateUnaryOp);
        String _translateExpr = this.translateExpr(((UnaryExpression)expr).getRight());
        _builder.append(_translateExpr);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof CastExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((CastExpression)expr).getRight());
        _builder.append(_translateExpr);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof MultiplicativeExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((MultiplicativeExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" ");
        {
          MultiplicativeOp _mulOp = ((MultiplicativeExpression)expr).getMulOp();
          boolean _equals = Objects.equal(_mulOp, MultiplicativeOp.MOD);
          if (_equals) {
            _builder.append("MOD");
          } else {
            MultiplicativeOp _mulOp_1 = ((MultiplicativeExpression)expr).getMulOp();
            _builder.append(_mulOp_1);
          }
        }
        _builder.append(" ");
        String _translateExpr_1 = this.translateExpr(((MultiplicativeExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof AdditiveExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((AdditiveExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" ");
        AdditiveOp _addOp = ((AdditiveExpression)expr).getAddOp();
        _builder.append(_addOp);
        _builder.append(" ");
        String _translateExpr_1 = this.translateExpr(((AdditiveExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof CheckStateExpression) {
        _matched=true;
        return this.translateCheckStateExpression(((CheckStateExpression)expr));
      }
    }
    if (!_matched) {
      if (expr instanceof CompareExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((CompareExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" ");
        CompareOp _cmpOp = ((CompareExpression)expr).getCmpOp();
        _builder.append(_cmpOp);
        _builder.append(" ");
        String _translateExpr_1 = this.translateExpr(((CompareExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof EqualityExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((EqualityExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" ");
        {
          CompareEqOp _eqCmpOp = ((EqualityExpression)expr).getEqCmpOp();
          boolean _equals = Objects.equal(_eqCmpOp, CompareEqOp.NOT_EQ);
          if (_equals) {
            _builder.append("<>");
          } else {
            _builder.append("=");
          }
        }
        _builder.append(" ");
        String _translateExpr_1 = this.translateExpr(((EqualityExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof BitAndExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((BitAndExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" AND ");
        String _translateExpr_1 = this.translateExpr(((BitAndExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof BitXorExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((BitXorExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" XOR ");
        String _translateExpr_1 = this.translateExpr(((BitXorExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof BitOrExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((BitOrExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" OR ");
        String _translateExpr_1 = this.translateExpr(((BitOrExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof LogicalAndExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((LogicalAndExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" AND ");
        String _translateExpr_1 = this.translateExpr(((LogicalAndExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof LogicalOrExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((LogicalOrExpression)expr).getLeft());
        _builder.append(_translateExpr);
        _builder.append(" OR ");
        String _translateExpr_1 = this.translateExpr(((LogicalOrExpression)expr).getRight());
        _builder.append(_translateExpr_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (expr instanceof AssignmentExpression) {
        _matched=true;
        boolean _hasAssignment = ExpressionUtil.hasAssignment(((AssignmentExpression)expr));
        if (_hasAssignment) {
          return this.translateAssignmentExpression(((AssignmentExpression)expr));
        }
        StringConcatenation _builder = new StringConcatenation();
        String _translateExpr = this.translateExpr(((AssignmentExpression)expr).getExpr());
        _builder.append(_translateExpr);
        return _builder.toString();
      }
    }
    return null;
  }
  
  public String translateAssignmentExpression(final AssignmentExpression ae) {
    AssignOperator _assignOp = ae.getAssignOp();
    if (_assignOp != null) {
      switch (_assignOp) {
        case ASSIGN:
          StringConcatenation _builder = new StringConcatenation();
          String _translateExpr = this.translateExpr(ae.getAssignVar());
          _builder.append(_translateExpr);
          _builder.append(" := ");
          String _translateExpr_1 = this.translateExpr(ae.getExpr());
          _builder.append(_translateExpr_1);
          return _builder.toString();
        case MUL:
          StringConcatenation _builder_1 = new StringConcatenation();
          String _translateExpr_2 = this.translateExpr(ae.getAssignVar());
          _builder_1.append(_translateExpr_2);
          _builder_1.append(" := ");
          String _translateExpr_3 = this.translateExpr(ae.getAssignVar());
          _builder_1.append(_translateExpr_3);
          _builder_1.append(" * ");
          String _translateExpr_4 = this.translateExpr(ae.getExpr());
          _builder_1.append(_translateExpr_4);
          return _builder_1.toString();
        case DIV:
          StringConcatenation _builder_2 = new StringConcatenation();
          String _translateExpr_5 = this.translateExpr(ae.getAssignVar());
          _builder_2.append(_translateExpr_5);
          _builder_2.append(" := ");
          String _translateExpr_6 = this.translateExpr(ae.getAssignVar());
          _builder_2.append(_translateExpr_6);
          _builder_2.append(" / ");
          String _translateExpr_7 = this.translateExpr(ae.getExpr());
          _builder_2.append(_translateExpr_7);
          return _builder_2.toString();
        case MOD:
          StringConcatenation _builder_3 = new StringConcatenation();
          String _translateExpr_8 = this.translateExpr(ae.getAssignVar());
          _builder_3.append(_translateExpr_8);
          _builder_3.append(" := ");
          String _translateExpr_9 = this.translateExpr(ae.getAssignVar());
          _builder_3.append(_translateExpr_9);
          _builder_3.append(" + ");
          String _translateExpr_10 = this.translateExpr(ae.getExpr());
          _builder_3.append(_translateExpr_10);
          return _builder_3.toString();
        case SUB:
          StringConcatenation _builder_4 = new StringConcatenation();
          String _translateExpr_11 = this.translateExpr(ae.getAssignVar());
          _builder_4.append(_translateExpr_11);
          _builder_4.append(" := ");
          String _translateExpr_12 = this.translateExpr(ae.getAssignVar());
          _builder_4.append(_translateExpr_12);
          _builder_4.append(" - ");
          String _translateExpr_13 = this.translateExpr(ae.getExpr());
          _builder_4.append(_translateExpr_13);
          return _builder_4.toString();
        case BIT_AND:
          StringConcatenation _builder_5 = new StringConcatenation();
          String _translateExpr_14 = this.translateExpr(ae.getAssignVar());
          _builder_5.append(_translateExpr_14);
          _builder_5.append(" := ");
          String _translateExpr_15 = this.translateExpr(ae.getAssignVar());
          _builder_5.append(_translateExpr_15);
          _builder_5.append(" AND ");
          String _translateExpr_16 = this.translateExpr(ae.getExpr());
          _builder_5.append(_translateExpr_16);
          return _builder_5.toString();
        case BIT_OR:
          StringConcatenation _builder_6 = new StringConcatenation();
          String _translateExpr_17 = this.translateExpr(ae.getAssignVar());
          _builder_6.append(_translateExpr_17);
          _builder_6.append(" := ");
          String _translateExpr_18 = this.translateExpr(ae.getAssignVar());
          _builder_6.append(_translateExpr_18);
          _builder_6.append(" OR ");
          String _translateExpr_19 = this.translateExpr(ae.getExpr());
          _builder_6.append(_translateExpr_19);
          return _builder_6.toString();
        case BIT_XOR:
          StringConcatenation _builder_7 = new StringConcatenation();
          String _translateExpr_20 = this.translateExpr(ae.getAssignVar());
          _builder_7.append(_translateExpr_20);
          _builder_7.append(" := ");
          String _translateExpr_21 = this.translateExpr(ae.getAssignVar());
          _builder_7.append(_translateExpr_21);
          _builder_7.append(" XOR ");
          String _translateExpr_22 = this.translateExpr(ae.getExpr());
          _builder_7.append(_translateExpr_22);
          return _builder_7.toString();
        default:
          StringConcatenation _builder_8 = new StringConcatenation();
          String _translateExpr_23 = this.translateExpr(ae.getAssignVar());
          _builder_8.append(_translateExpr_23);
          _builder_8.append(" := ");
          String _translateExpr_24 = this.translateExpr(ae.getAssignVar());
          _builder_8.append(_translateExpr_24);
          return _builder_8.toString();
      }
    } else {
      StringConcatenation _builder_8 = new StringConcatenation();
      String _translateExpr_23 = this.translateExpr(ae.getAssignVar());
      _builder_8.append(_translateExpr_23);
      _builder_8.append(" := ");
      String _translateExpr_24 = this.translateExpr(ae.getAssignVar());
      _builder_8.append(_translateExpr_24);
      return _builder_8.toString();
    }
  }
  
  public Type resolveIdReferenceType(final IdReference ref) {
    boolean _matched = false;
    if (ref instanceof PhysicalVariable) {
      _matched=true;
      return ((PhysicalVariable)ref).getType();
    }
    if (!_matched) {
      if (ref instanceof ProgramVariable) {
        _matched=true;
        return ((ProgramVariable)ref).getType();
      }
    }
    if (!_matched) {
      if (ref instanceof EnumMember) {
        _matched=true;
        return this.resolveExprType(((EnumMember)ref).getValue());
      }
    }
    if (!_matched) {
      if (ref instanceof Const) {
        _matched=true;
        return this.resolveExprType(((Const)ref).getValue());
      }
    }
    return null;
  }
  
  public Type resolveExprType(final EObject expr) {
    Type _switchResult = null;
    boolean _matched = false;
    if (expr instanceof InfixOp) {
      _matched=true;
      return this.resolveExprType(((InfixOp)expr).getRef());
    }
    if (!_matched) {
      if (expr instanceof PostfixOp) {
        _matched=true;
        return this.resolveExprType(((PostfixOp)expr).getRef());
      }
    }
    if (!_matched) {
      if (expr instanceof FunctionCall) {
        _matched=true;
        return ((FunctionCall)expr).getFunction().getReturnType();
      }
    }
    if (!_matched) {
      if (expr instanceof IdReference) {
        _matched=true;
        return this.resolveIdReferenceType(((IdReference)expr));
      }
    }
    if (!_matched) {
      if (expr instanceof PrimaryExpression) {
        _matched=true;
        boolean _isNestedExpr = ExpressionUtil.isNestedExpr(((PrimaryExpression)expr));
        if (_isNestedExpr) {
          return this.resolveExprType(((PrimaryExpression)expr).getNestedExpr());
        }
        boolean _isBoolean = ExpressionUtil.isBoolean(((PrimaryExpression)expr));
        if (_isBoolean) {
          return Type.BOOL_EN;
        }
        boolean _isReference = ExpressionUtil.isReference(((PrimaryExpression)expr));
        if (_isReference) {
          return this.resolveExprType(((PrimaryExpression)expr).getReference());
        }
        boolean _isInteger = ExpressionUtil.isInteger(((PrimaryExpression)expr));
        if (_isInteger) {
          return Type.LONG_EN;
        }
        boolean _isFloating = ExpressionUtil.isFloating(((PrimaryExpression)expr));
        if (_isFloating) {
          return Type.DOUBLE_EN;
        }
      }
    }
    if (!_matched) {
      if (expr instanceof UnaryExpression) {
        _matched=true;
        return this.resolveExprType(((UnaryExpression)expr).getRight());
      }
    }
    if (!_matched) {
      if (expr instanceof CastExpression) {
        _matched=true;
        return ((CastExpression)expr).getType();
      }
    }
    if (!_matched) {
      if (expr instanceof CheckStateExpression) {
        _matched=true;
        return Type.BOOL_EN;
      }
    }
    if (!_matched) {
      if (expr instanceof AssignmentExpression) {
        _matched=true;
        boolean _hasAssignment = ExpressionUtil.hasAssignment(((AssignmentExpression)expr));
        if (_hasAssignment) {
          return this.resolveExprType(((AssignmentExpression)expr).getAssignVar());
        }
        return this.resolveExprType(((AssignmentExpression)expr).getExpr());
      }
    }
    if (!_matched) {
      _switchResult = this.resolveBinaryExprType(expr);
    }
    return _switchResult;
  }
  
  public Type resolveBinaryExprType(final EObject expr) {
    EObject left = null;
    EObject right = null;
    boolean _matched = false;
    if (expr instanceof MultiplicativeExpression) {
      _matched=true;
      left = ((MultiplicativeExpression)expr).getLeft();
      right = ((MultiplicativeExpression)expr).getRight();
    }
    if (!_matched) {
      if (expr instanceof AdditiveExpression) {
        _matched=true;
        left = ((AdditiveExpression)expr).getLeft();
        right = ((AdditiveExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof ShiftExpression) {
        _matched=true;
        left = ((ShiftExpression)expr).getLeft();
        right = ((ShiftExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof CompareExpression) {
        _matched=true;
        left = ((CompareExpression)expr).getLeft();
        right = ((CompareExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof EqualityExpression) {
        _matched=true;
        left = ((EqualityExpression)expr).getLeft();
        right = ((EqualityExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof BitAndExpression) {
        _matched=true;
        left = ((BitAndExpression)expr).getLeft();
        right = ((BitAndExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof BitXorExpression) {
        _matched=true;
        left = ((BitXorExpression)expr).getLeft();
        right = ((BitXorExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof BitOrExpression) {
        _matched=true;
        left = ((BitOrExpression)expr).getLeft();
        right = ((BitOrExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof LogicalAndExpression) {
        _matched=true;
        left = ((LogicalAndExpression)expr).getLeft();
        right = ((LogicalAndExpression)expr).getRight();
      }
    }
    if (!_matched) {
      if (expr instanceof LogicalOrExpression) {
        _matched=true;
        left = ((LogicalOrExpression)expr).getLeft();
        right = ((LogicalOrExpression)expr).getRight();
      }
    }
    final Type leftType = this.resolveExprType(left);
    final Type rightType = this.resolveExprType(right);
    return this.max(leftType, rightType);
  }
}
