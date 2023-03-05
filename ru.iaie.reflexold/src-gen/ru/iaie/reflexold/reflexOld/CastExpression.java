/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cast Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.CastExpression#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getCastExpression()
 * @model
 * @generated
 */
public interface CastExpression extends MultiplicativeExpression
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link ru.iaie.reflexold.reflexOld.Type}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see ru.iaie.reflexold.reflexOld.Type
   * @see #setType(Type)
   * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getCastExpression_Type()
   * @model
   * @generated
   */
  Type getType();

  /**
   * Sets the value of the '{@link ru.iaie.reflexold.reflexOld.CastExpression#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see ru.iaie.reflexold.reflexOld.Type
   * @see #getType()
   * @generated
   */
  void setType(Type value);

} // CastExpression
