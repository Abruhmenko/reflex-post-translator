package ru.iaie.reflexold.generator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import ru.iaie.reflexold.reflexOld.Const;
import ru.iaie.reflexold.reflexOld.EnumMember;
import ru.iaie.reflexold.reflexOld.Function;
import ru.iaie.reflexold.reflexOld.PhysicalVariable;
import ru.iaie.reflexold.reflexOld.Port;
import ru.iaie.reflexold.reflexOld.PortType;
import ru.iaie.reflexold.reflexOld.ProcessVariable;
import ru.iaie.reflexold.reflexOld.State;
import ru.iaie.reflexold.reflexOld.Statement;
import ru.iaie.reflexold.reflexOld.TimeoutFunction;
import ru.iaie.reflexold.reflexOld.Type;
import ru.iaie.reflexold.utils.ReflexOldModelUtil;

@SuppressWarnings("all")
public class GeneratorHelper {
  protected ExpressionGenerator expressionGenerator;
  
  protected StatementGenerator statementGenerator;
  
  protected int enumCount;
  
  public GeneratorHelper(final boolean isEnglish) {
    ExpressionGenerator _expressionGenerator = new ExpressionGenerator(isEnglish);
    this.expressionGenerator = _expressionGenerator;
    StatementGenerator _statementGenerator = new StatementGenerator(this.expressionGenerator);
    this.statementGenerator = _statementGenerator;
    this.enumCount = 0;
  }
  
  public String translateName(final String name) {
    return this.expressionGenerator.toID(name);
  }
  
  public String translateArgType(final Type t) {
    return this.expressionGenerator.translateType(t);
  }
  
  public String translateProcVarName(final ProcessVariable pv) {
    return this.expressionGenerator.getMapping(pv);
  }
  
  public String translatePortMapping(final PhysicalVariable physVar) {
    StringConcatenation _builder = new StringConcatenation();
    String _translateName = this.translateName(physVar.getMapping().getPort().getName());
    _builder.append(_translateName);
    _builder.append("[");
    {
      boolean _isSingleBitMapping = ReflexOldModelUtil.isSingleBitMapping(physVar.getMapping());
      if (_isSingleBitMapping) {
        _builder.append("0");
      }
    }
    _builder.append("]");
    return _builder.toString();
  }
  
  public String generateConstantDefinition(final Const c) {
    final Type constType = this.expressionGenerator.resolveExprType(c.getValue());
    StringConcatenation _builder = new StringConcatenation();
    String _mapping = this.expressionGenerator.getMapping(c);
    _builder.append(_mapping);
    _builder.append(" : ");
    String _translateType = this.expressionGenerator.translateType(constType);
    _builder.append(_translateType);
    _builder.append(" := ");
    String _translateExpr = this.expressionGenerator.translateExpr(c.getValue());
    _builder.append(_translateExpr);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  /**
   * def generateEnumDefinition(Enum en) {
   * var result = '''
   * enum enumerator_�enumCount� {
   * �FOR enumMember : en.enumMembers�
   * �expressionGenerator.getMapping(enumMember)��IF enumMember.hasValue� = �expressionGenerator.translateExpr(enumMember.value)��ENDIF��IF isEnumMemberLast(enumMember, en)��ELSE�,�ENDIF�
   * �ENDFOR�
   * }
   * '''
   * enumCount++
   * return result
   * }
   */
  public String generateEnumVarDefinition(final ru.iaie.reflexold.reflexOld.Enum en) {
    String result = "\n";
    String prevName = "";
    EList<EnumMember> _enumMembers = en.getEnumMembers();
    for (final EnumMember enumMember : _enumMembers) {
      {
        String _result = result;
        String _generateEnumMemberVarDefinition = this.generateEnumMemberVarDefinition(enumMember, prevName);
        result = (_result + _generateEnumMemberVarDefinition);
        prevName = this.expressionGenerator.getMapping(enumMember);
      }
    }
    return result;
  }
  
  public String generateEnumMemberVarDefinition(final EnumMember em, final String prevName) {
    StringConcatenation _builder = new StringConcatenation();
    String _mapping = this.expressionGenerator.getMapping(em);
    _builder.append(_mapping);
    _builder.append(" : INT := ");
    {
      boolean _hasValue = ReflexOldModelUtil.hasValue(em);
      if (_hasValue) {
        String _translateExpr = this.expressionGenerator.translateExpr(em.getValue());
        _builder.append(_translateExpr);
      } else {
        boolean _isEmpty = prevName.isEmpty();
        if (_isEmpty) {
          _builder.append("0");
        } else {
          _builder.append(prevName);
          _builder.append(" + 1");
        }
      }
    }
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public String generateFunctionDefinition(final Function f) {
    StringConcatenation _builder = new StringConcatenation();
    String _translateType = this.expressionGenerator.translateType(f.getReturnType());
    _builder.append(_translateType);
    _builder.append(" ");
    String _name = f.getName();
    _builder.append(_name);
    _builder.append("(");
    final Function1<Type, String> _function = (Type it) -> {
      return this.translateArgType(it);
    };
    String _join = String.join(", ", ListExtensions.<Type, String>map(f.getArgTypes(), _function));
    _builder.append(_join);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public CharSequence generatePortDefinition(final Port p) {
    CharSequence _switchResult = null;
    PortType _type = p.getType();
    if (_type != null) {
      switch (_type) {
        case INPUT_EN:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("input ");
          String _translateName = this.translateName(p.getName());
          _builder.append(_translateName);
          _builder.append(" ");
          String _addr1 = p.getAddr1();
          _builder.append(_addr1);
          _builder.append(" ");
          String _addr2 = p.getAddr2();
          _builder.append(_addr2);
          _builder.append(" ");
          String _size = p.getSize();
          _builder.append(_size);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          return _builder.toString();
        case INPUT_RU:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("input ");
          String _translateName_1 = this.translateName(p.getName());
          _builder_1.append(_translateName_1);
          _builder_1.append(" ");
          String _addr1_1 = p.getAddr1();
          _builder_1.append(_addr1_1);
          _builder_1.append(" ");
          String _addr2_1 = p.getAddr2();
          _builder_1.append(_addr2_1);
          _builder_1.append(" ");
          String _size_1 = p.getSize();
          _builder_1.append(_size_1);
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
          return _builder_1.toString();
        case OUTPUT_EN:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("output ");
          String _translateName_2 = this.translateName(p.getName());
          _builder_2.append(_translateName_2);
          _builder_2.append(" ");
          String _addr1_2 = p.getAddr1();
          _builder_2.append(_addr1_2);
          _builder_2.append(" ");
          String _addr2_2 = p.getAddr2();
          _builder_2.append(_addr2_2);
          _builder_2.append(" ");
          String _size_2 = p.getSize();
          _builder_2.append(_size_2);
          _builder_2.append(";");
          _builder_2.newLineIfNotEmpty();
          return _builder_2.toString();
        case OUTPUT_RU:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("output ");
          String _translateName_3 = this.translateName(p.getName());
          _builder_3.append(_translateName_3);
          _builder_3.append(" ");
          String _addr1_3 = p.getAddr1();
          _builder_3.append(_addr1_3);
          _builder_3.append(" ");
          String _addr2_3 = p.getAddr2();
          _builder_3.append(_addr2_3);
          _builder_3.append(" ");
          String _size_3 = p.getSize();
          _builder_3.append(_size_3);
          _builder_3.append(";");
          _builder_3.newLineIfNotEmpty();
          _switchResult = _builder_3;
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  /**
   * Process:
   * ("PROC" | "����") name=ID "{"
   * ((imports+=ImportedVariableList | variables+=ProcessVariable) ";")
   * states+=State
   * "}";
   * 
   * ImportedVariableList:
   * (("FROM" "PROC") | ("��" "����")) process=[Process]
   * variables+=[ProcessVariable] ("," variables+=[ProcessVariable])*;
   * 
   * ProcessVariable:
   * (PhysicalVariable | ProgramVariable)
   * (
   * (local?=("LOCAL" | "�����")) |
   * (shared?=("FOR" | "���")
   * (
   * ("ALL" | "����") |
   * ((("PROC") | ("����")) processes+=[Process] ("," processes+=[Process])*)
   * )
   * )
   * );
   */
  public String generateProcessDefinition(final ru.iaie.reflexold.reflexOld.Process p) {
    boolean needVarBlock = false;
    EList<ProcessVariable> _variables = p.getVariables();
    for (final ProcessVariable pv : _variables) {
      boolean _isShared = ReflexOldModelUtil.isShared(pv);
      boolean _not = (!_isShared);
      if (_not) {
        needVarBlock = true;
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("PROCESS ");
    String _translateName = this.translateName(p.getName());
    _builder.append(_translateName);
    _builder.newLineIfNotEmpty();
    {
      if (needVarBlock) {
        _builder.append("\t");
        _builder.append("VAR");
        _builder.newLine();
        {
          EList<ProcessVariable> _variables_1 = p.getVariables();
          for(final ProcessVariable procVar : _variables_1) {
            {
              boolean _isShared_1 = ReflexOldModelUtil.isShared(procVar);
              boolean _not_1 = (!_isShared_1);
              if (_not_1) {
                _builder.append("\t");
                _builder.append("\t");
                String _generateProcVarDefinition = this.generateProcVarDefinition(procVar);
                _builder.append(_generateProcVarDefinition, "\t\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("END_VAR");
        _builder.newLine();
      }
    }
    {
      EList<State> _states = p.getStates();
      for(final State state : _states) {
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        String _generateStateDefinition = this.generateStateDefinition(state);
        _builder.append(_generateStateDefinition, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("END_PROCESS");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
  
  /**
   * ProgramVariable:
   * type=Type name=ID;
   * PhysicalVariable:
   * type=Type name=ID "=" mapping=PortMapping;
   * PortMapping:
   * "{" port=[Port] "[" (bits=INTEGER) "]" "}";
   * Port:
   * type=PortType name=ID addr1=INTEGER addr2=INTEGER size=INTEGER ";";
   */
  public String generateProcVarDefinition(final ProcessVariable pv) {
    StringConcatenation _builder = new StringConcatenation();
    String _translateName = this.translateName(ReflexOldModelUtil.getName(pv));
    _builder.append(_translateName);
    _builder.append(" : ");
    String _translateType = this.expressionGenerator.translateType(ReflexOldModelUtil.getType(pv));
    _builder.append(_translateType);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public String generateStateDefinition(final State state) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("STATE ");
    String _translateName = this.translateName(state.getName());
    _builder.append(_translateName);
    _builder.append(" ");
    {
      boolean _isStateLooped = this.statementGenerator.isStateLooped(state);
      if (_isStateLooped) {
        _builder.append("LOOPED ");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      EList<Statement> _statements = state.getStateFunction().getStatements();
      for(final Statement stat : _statements) {
        _builder.append("\t");
        String _translateStatement = this.statementGenerator.translateStatement(state, stat);
        _builder.append(_translateStatement, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      TimeoutFunction _timeoutFunction = state.getTimeoutFunction();
      boolean _tripleNotEquals = (_timeoutFunction != null);
      if (_tripleNotEquals) {
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        String _translateTimeoutFunction = this.statementGenerator.translateTimeoutFunction(state, state.getTimeoutFunction());
        _builder.append(_translateTimeoutFunction, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("END_STATE");
    _builder.newLine();
    return _builder.toString();
  }
}
