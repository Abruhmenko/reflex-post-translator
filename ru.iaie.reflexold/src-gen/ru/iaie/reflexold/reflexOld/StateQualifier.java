/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>State Qualifier</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getStateQualifier()
 * @model
 * @generated
 */
public enum StateQualifier implements Enumerator
{
  /**
   * The '<em><b>ACTIVE EN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ACTIVE_EN_VALUE
   * @generated
   * @ordered
   */
  ACTIVE_EN(0, "ACTIVE_EN", "ACTIVE"),

  /**
   * The '<em><b>ACTIVE RU</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ACTIVE_RU_VALUE
   * @generated
   * @ordered
   */
  ACTIVE_RU(1, "ACTIVE_RU", "\u0410\u041a\u0422\u0418\u0412\u041d\u041e\u0415"),

  /**
   * The '<em><b>PASSIVE EN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PASSIVE_EN_VALUE
   * @generated
   * @ordered
   */
  PASSIVE_EN(2, "PASSIVE_EN", "PASSIVE"),

  /**
   * The '<em><b>PASSIVE RU</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PASSIVE_RU_VALUE
   * @generated
   * @ordered
   */
  PASSIVE_RU(3, "PASSIVE_RU", "\u041f\u0410\u0421\u0421\u0418\u0412\u041d\u041e\u0415"),

  /**
   * The '<em><b>STOP EN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #STOP_EN_VALUE
   * @generated
   * @ordered
   */
  STOP_EN(4, "STOP_EN", "STOP"),

  /**
   * The '<em><b>STOP RU</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #STOP_RU_VALUE
   * @generated
   * @ordered
   */
  STOP_RU(5, "STOP_RU", "\u0421\u0422\u041e\u041f"),

  /**
   * The '<em><b>ERROR EN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ERROR_EN_VALUE
   * @generated
   * @ordered
   */
  ERROR_EN(6, "ERROR_EN", "ERROR"),

  /**
   * The '<em><b>ERROR RU</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ERROR_RU_VALUE
   * @generated
   * @ordered
   */
  ERROR_RU(7, "ERROR_RU", "\u041e\u0428\u0418\u0411\u041a\u0410");

  /**
   * The '<em><b>ACTIVE EN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ACTIVE_EN
   * @model literal="ACTIVE"
   * @generated
   * @ordered
   */
  public static final int ACTIVE_EN_VALUE = 0;

  /**
   * The '<em><b>ACTIVE RU</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ACTIVE_RU
   * @model literal="\u0410\u041a\u0422\u0418\u0412\u041d\u041e\u0415"
   * @generated
   * @ordered
   */
  public static final int ACTIVE_RU_VALUE = 1;

  /**
   * The '<em><b>PASSIVE EN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PASSIVE_EN
   * @model literal="PASSIVE"
   * @generated
   * @ordered
   */
  public static final int PASSIVE_EN_VALUE = 2;

  /**
   * The '<em><b>PASSIVE RU</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PASSIVE_RU
   * @model literal="\u041f\u0410\u0421\u0421\u0418\u0412\u041d\u041e\u0415"
   * @generated
   * @ordered
   */
  public static final int PASSIVE_RU_VALUE = 3;

  /**
   * The '<em><b>STOP EN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #STOP_EN
   * @model literal="STOP"
   * @generated
   * @ordered
   */
  public static final int STOP_EN_VALUE = 4;

  /**
   * The '<em><b>STOP RU</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #STOP_RU
   * @model literal="\u0421\u0422\u041e\u041f"
   * @generated
   * @ordered
   */
  public static final int STOP_RU_VALUE = 5;

  /**
   * The '<em><b>ERROR EN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ERROR_EN
   * @model literal="ERROR"
   * @generated
   * @ordered
   */
  public static final int ERROR_EN_VALUE = 6;

  /**
   * The '<em><b>ERROR RU</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ERROR_RU
   * @model literal="\u041e\u0428\u0418\u0411\u041a\u0410"
   * @generated
   * @ordered
   */
  public static final int ERROR_RU_VALUE = 7;

  /**
   * An array of all the '<em><b>State Qualifier</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final StateQualifier[] VALUES_ARRAY =
    new StateQualifier[]
    {
      ACTIVE_EN,
      ACTIVE_RU,
      PASSIVE_EN,
      PASSIVE_RU,
      STOP_EN,
      STOP_RU,
      ERROR_EN,
      ERROR_RU,
    };

  /**
   * A public read-only list of all the '<em><b>State Qualifier</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<StateQualifier> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>State Qualifier</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static StateQualifier get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      StateQualifier result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>State Qualifier</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static StateQualifier getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      StateQualifier result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>State Qualifier</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static StateQualifier get(int value)
  {
    switch (value)
    {
      case ACTIVE_EN_VALUE: return ACTIVE_EN;
      case ACTIVE_RU_VALUE: return ACTIVE_RU;
      case PASSIVE_EN_VALUE: return PASSIVE_EN;
      case PASSIVE_RU_VALUE: return PASSIVE_RU;
      case STOP_EN_VALUE: return STOP_EN;
      case STOP_RU_VALUE: return STOP_RU;
      case ERROR_EN_VALUE: return ERROR_EN;
      case ERROR_RU_VALUE: return ERROR_RU;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private StateQualifier(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //StateQualifier
