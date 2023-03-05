package ru.iaie.reflexold.generator;

import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Conversions;
import ru.iaie.reflexold.reflexOld.AssignmentExpression;
import ru.iaie.reflexold.reflexOld.CaseStat;
import ru.iaie.reflexold.reflexOld.CompoundStatement;
import ru.iaie.reflexold.reflexOld.ErrorStat;
import ru.iaie.reflexold.reflexOld.Expression;
import ru.iaie.reflexold.reflexOld.FunctionCall;
import ru.iaie.reflexold.reflexOld.IfElseStat;
import ru.iaie.reflexold.reflexOld.LoopStat;
import ru.iaie.reflexold.reflexOld.ResetStat;
import ru.iaie.reflexold.reflexOld.RestartStat;
import ru.iaie.reflexold.reflexOld.SetStateStat;
import ru.iaie.reflexold.reflexOld.StartProcStat;
import ru.iaie.reflexold.reflexOld.State;
import ru.iaie.reflexold.reflexOld.Statement;
import ru.iaie.reflexold.reflexOld.StopProcStat;
import ru.iaie.reflexold.reflexOld.SwitchStat;
import ru.iaie.reflexold.reflexOld.TimeoutFunction;
import ru.iaie.reflexold.utils.ExpressionUtil;
import ru.iaie.reflexold.utils.LiteralUtil;
import ru.iaie.reflexold.utils.ReflexOldModelUtil;

@SuppressWarnings("all")
public class StatementGenerator {
  protected ExpressionGenerator expressionGenerator;
  
  public StatementGenerator(final ExpressionGenerator expressionGenerator) {
    this.expressionGenerator = expressionGenerator;
  }
  
  public String translateName(final String name) {
    return this.expressionGenerator.toID(name);
  }
  
  public String translateTimeoutFunction(final State state, final TimeoutFunction func) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("TIMEOUT ");
    Object _translateTimeout = this.translateTimeout(func);
    _builder.append(_translateTimeout);
    _builder.append(" THEN");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _translateStatement = this.translateStatement(state, func.getBody());
    _builder.append(_translateStatement, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("END_TIMEOUT");
    _builder.newLine();
    return _builder.toString();
  }
  
  public Object translateTimeout(final TimeoutFunction func) {
    String _xblockexpression = null;
    {
      boolean _isClearTimeout = ReflexOldModelUtil.isClearTimeout(func);
      if (_isClearTimeout) {
        return Long.valueOf(LiteralUtil.parseInteger(func.getTime()));
      }
      String _xifexpression = null;
      boolean _isReferencedTimeout = ReflexOldModelUtil.isReferencedTimeout(func);
      if (_isReferencedTimeout) {
        _xifexpression = this.expressionGenerator.getMapping(func.getRef());
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public String translateStatement(final State state, final EObject statement) {
    boolean _matched = false;
    if (statement instanceof StopProcStat) {
      _matched=true;
      return this.translateStopProcStat(((StopProcStat)statement));
    }
    if (!_matched) {
      if (statement instanceof ErrorStat) {
        _matched=true;
        return this.translateErrorProcStat(((ErrorStat)statement));
      }
    }
    if (!_matched) {
      if (statement instanceof SetStateStat) {
        _matched=true;
        return this.translateSetStateStat(state, ((SetStateStat)statement));
      }
    }
    if (!_matched) {
      if (statement instanceof IfElseStat) {
        _matched=true;
        return this.translateIfElseStat(state, ((IfElseStat)statement));
      }
    }
    if (!_matched) {
      if (statement instanceof Expression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        {
          if ((((statement instanceof AssignmentExpression) && (!ExpressionUtil.hasAssignment(((AssignmentExpression) statement)))) && (((AssignmentExpression) statement).getExpr() instanceof FunctionCall))) {
            _builder.append("_VOID_ := ");
          }
        }
        String _translateExpr = this.expressionGenerator.translateExpr(statement);
        _builder.append(_translateExpr);
        _builder.append(";");
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (statement instanceof SwitchStat) {
        _matched=true;
        return this.translateSwitchCaseStat(state, ((SwitchStat)statement));
      }
    }
    if (!_matched) {
      if (statement instanceof StartProcStat) {
        _matched=true;
        return this.translateStartProcStat(((StartProcStat)statement));
      }
    }
    if (!_matched) {
      if (statement instanceof ResetStat) {
        _matched=true;
        return this.translateResetTimer();
      }
    }
    if (!_matched) {
      if (statement instanceof RestartStat) {
        _matched=true;
        return this.translateRestartProcStat();
      }
    }
    if (!_matched) {
      if (statement instanceof CompoundStatement) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        {
          EList<Statement> _statements = ((CompoundStatement)statement).getStatements();
          for(final Statement stat : _statements) {
            String _translateStatement = this.translateStatement(state, stat);
            _builder.append(_translateStatement);
            _builder.newLineIfNotEmpty();
          }
        }
        return _builder.toString();
      }
    }
    return null;
  }
  
  public String translateIfElseStat(final State state, final IfElseStat stat) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("IF (");
    String _translateExpr = this.expressionGenerator.translateExpr(stat.getCond());
    _builder.append(_translateExpr);
    _builder.append(") THEN");
    _builder.newLineIfNotEmpty();
    {
      if (((stat.getThen() instanceof CompoundStatement) && (ReflexOldModelUtil.isEmpty(((CompoundStatement) stat.getThen())) || Objects.equal(NodeModelUtils.getNode(((CompoundStatement) stat.getThen()).getStatements().get(0)).getText().trim(), ";")))) {
        _builder.append("\t_VOID_ := _VOID_;");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _translateStatement = this.translateStatement(state, stat.getThen());
    _builder.append(_translateStatement, "\t");
    _builder.newLineIfNotEmpty();
    {
      Statement _else = stat.getElse();
      boolean _tripleNotEquals = (_else != null);
      if (_tripleNotEquals) {
        _builder.append("ELSE");
        _builder.newLine();
        _builder.append("\t");
        String _translateStatement_1 = this.translateStatement(state, stat.getElse());
        _builder.append(_translateStatement_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("END_IF");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String translateSwitchCaseStat(final State state, final SwitchStat stat) {
    int countEmptyOptionsAtEnd = 0;
    int optionsLength = ((Object[])Conversions.unwrapArray(stat.getOptions(), Object.class)).length;
    EList<CaseStat> _options = stat.getOptions();
    for (final CaseStat variant : _options) {
      if (((variant.getStatements().isEmpty() && (!variant.isHasBreak())) || (((((Object[])Conversions.unwrapArray(variant.getStatements(), Object.class)).length == 1) && Objects.equal(NodeModelUtils.getNode(variant.getStatements().get(0)).getText().trim(), ";")) && (!variant.isHasBreak())))) {
        int _countEmptyOptionsAtEnd = countEmptyOptionsAtEnd;
        countEmptyOptionsAtEnd = (_countEmptyOptionsAtEnd + 1);
      } else {
        countEmptyOptionsAtEnd = 0;
      }
    }
    List<CaseStat> newOptions = stat.getOptions().subList(0, (optionsLength - countEmptyOptionsAtEnd));
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CASE (");
    String _translateExpr = this.expressionGenerator.translateExpr(stat.getExpr());
    _builder.append(_translateExpr);
    _builder.append(") OF");
    _builder.newLineIfNotEmpty();
    {
      for(final CaseStat variant_1 : newOptions) {
        {
          if (((variant_1.getStatements().isEmpty() && (!variant_1.isHasBreak())) || (((((Object[])Conversions.unwrapArray(variant_1.getStatements(), Object.class)).length == 1) && Objects.equal(NodeModelUtils.getNode(variant_1.getStatements().get(0)).getText().trim(), ";")) && (!variant_1.isHasBreak())))) {
            _builder.append("\t");
            String _translateExpr_1 = this.expressionGenerator.translateExpr(variant_1.getOption());
            _builder.append(_translateExpr_1, "\t");
            _builder.append(",");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            String _translateExpr_2 = this.expressionGenerator.translateExpr(variant_1.getOption());
            _builder.append(_translateExpr_2, "\t");
            _builder.append(":");
            _builder.newLineIfNotEmpty();
            {
              EList<Statement> _statements = variant_1.getStatements();
              for(final Statement statement : _statements) {
                _builder.append("\t");
                _builder.append("\t");
                {
                  if (((statement instanceof CompoundStatement) && (ReflexOldModelUtil.isEmpty(((CompoundStatement) statement)) || Objects.equal(NodeModelUtils.getNode(((CompoundStatement) statement).getStatements().get(0)).getText().trim(), ";")))) {
                    _builder.append("_VOID_ := _VOID_;");
                  }
                }
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                String _translateStatement = this.translateStatement(state, statement);
                _builder.append(_translateStatement, "\t\t");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            {
              boolean _isHasBreak = variant_1.isHasBreak();
              if (_isHasBreak) {
                _builder.append("_BREAK_ := _BREAK_;");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    {
      boolean _hasDefaultOption = ReflexOldModelUtil.hasDefaultOption(stat);
      if (_hasDefaultOption) {
        _builder.append("ELSE");
        _builder.newLineIfNotEmpty();
        {
          EList<Statement> _statements_1 = stat.getDefaultOption().getStatements();
          for(final Statement statement_1 : _statements_1) {
            _builder.append("\t");
            _builder.append("\t");
            String _translateStatement_1 = this.translateStatement(state, statement_1);
            _builder.append(_translateStatement_1, "\t\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        {
          boolean _isHasBreak_1 = stat.getDefaultOption().isHasBreak();
          if (_isHasBreak_1) {
            _builder.append("_BREAK_ := _BREAK_;");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("END_CASE");
    _builder.newLine();
    return _builder.toString();
  }
  
  /**
   * def translateSwitchCaseStat(State state, SwitchStat stat) {  // TODO: empty cases and only break; cases
   * return '''
   * CASE («expressionGenerator.translateExpr(stat.expr)») OF
   * «FOR variant : stat.options»
   * «expressionGenerator.translateExpr(variant.option)»:
   * «FOR statement: variant.statements»
   * «translateStatement(state, statement)»
   * «ENDFOR»
   * «IF variant.hasBreak»break;«ENDIF»
   * «ENDFOR»
   * «IF stat.hasDefaultOption»ELSE
   * «FOR statement: stat.defaultOption.statements»
   * «translateStatement(state, statement)»
   * «ENDFOR»
   * «IF stat.defaultOption.hasBreak»break;«ENDIF»
   * «ENDIF»
   * END_CASE
   * '''
   * }
   */
  public String translateResetTimer() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("RESET TIMER;");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String translateSetStateStat(final State state, final SetStateStat sss) {
    boolean _isNext = sss.isNext();
    if (_isNext) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SET NEXT;");
      _builder.newLine();
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("SET STATE ");
    String _translateName = this.translateName(sss.getState().getName());
    _builder_1.append(_translateName);
    _builder_1.append(";");
    _builder_1.newLineIfNotEmpty();
    return _builder_1.toString();
  }
  
  public String translateStopProcStat(final StopProcStat sps) {
    boolean _selfStop = ReflexOldModelUtil.selfStop(sps);
    if (_selfStop) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("STOP;");
      _builder.newLine();
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("STOP PROCESS ");
    String _translateName = this.translateName(sps.getProcess().getName());
    _builder_1.append(_translateName);
    _builder_1.append(";");
    _builder_1.newLineIfNotEmpty();
    return _builder_1.toString();
  }
  
  public String translateErrorProcStat(final ErrorStat es) {
    boolean _selfError = ReflexOldModelUtil.selfError(es);
    if (_selfError) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("ERROR;");
      _builder.newLine();
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("ERROR PROCESS ");
    String _translateName = this.translateName(es.getProcess().getName());
    _builder_1.append(_translateName);
    _builder_1.append(";");
    _builder_1.newLineIfNotEmpty();
    return _builder_1.toString();
  }
  
  public String translateStartProcStat(final StartProcStat sps) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("START PROCESS ");
    String _translateName = this.translateName(sps.getProcess().getName());
    _builder.append(_translateName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public String translateRestartProcStat() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("RESTART;");
    _builder.newLine();
    return _builder.toString();
  }
  
  public boolean isStateLooped(final State state) {
    EList<Statement> _statements = state.getStateFunction().getStatements();
    for (final Statement statement : _statements) {
      boolean _hasLoop = this.hasLoop(statement);
      if (_hasLoop) {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasLoop(final EObject statement) {
    boolean _matched = false;
    if (statement instanceof LoopStat) {
      _matched=true;
      return true;
    }
    if (!_matched) {
      if (statement instanceof IfElseStat) {
        _matched=true;
        boolean _hasLoop = this.hasLoop(((IfElseStat)statement).getThen());
        if (_hasLoop) {
          return true;
        }
        Statement _else = ((IfElseStat)statement).getElse();
        boolean _tripleNotEquals = (_else != null);
        if (_tripleNotEquals) {
          return this.hasLoop(((IfElseStat)statement).getElse());
        }
        return false;
      }
    }
    if (!_matched) {
      if (statement instanceof SwitchStat) {
        _matched=true;
        EList<CaseStat> _options = ((SwitchStat)statement).getOptions();
        for (final CaseStat variant : _options) {
          EList<Statement> _statements = variant.getStatements();
          for (final Statement stat : _statements) {
            boolean _hasLoop = this.hasLoop(stat);
            if (_hasLoop) {
              return true;
            }
          }
        }
        boolean _hasDefaultOption = ReflexOldModelUtil.hasDefaultOption(((SwitchStat)statement));
        if (_hasDefaultOption) {
          EList<Statement> _statements_1 = ((SwitchStat)statement).getDefaultOption().getStatements();
          for (final Statement stat_1 : _statements_1) {
            boolean _hasLoop_1 = this.hasLoop(stat_1);
            if (_hasLoop_1) {
              return true;
            }
          }
        }
        return false;
      }
    }
    if (!_matched) {
      if (statement instanceof CompoundStatement) {
        _matched=true;
        EList<Statement> _statements = ((CompoundStatement)statement).getStatements();
        for (final Statement stat : _statements) {
          boolean _hasLoop = this.hasLoop(stat);
          if (_hasLoop) {
            return true;
          }
        }
        return false;
      }
    }
    return false;
  }
}
