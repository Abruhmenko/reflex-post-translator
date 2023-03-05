/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.Function#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.Function#getName <em>Name</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.Function#getArgTypes <em>Arg Types</em>}</li>
 * </ul>
 *
 * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getFunction()
 * @model
 * @generated
 */
public interface Function extends EObject
{
  /**
   * Returns the value of the '<em><b>Return Type</b></em>' attribute.
   * The literals are from the enumeration {@link ru.iaie.reflexold.reflexOld.Type}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Return Type</em>' attribute.
   * @see ru.iaie.reflexold.reflexOld.Type
   * @see #setReturnType(Type)
   * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getFunction_ReturnType()
   * @model
   * @generated
   */
  Type getReturnType();

  /**
   * Sets the value of the '{@link ru.iaie.reflexold.reflexOld.Function#getReturnType <em>Return Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Return Type</em>' attribute.
   * @see ru.iaie.reflexold.reflexOld.Type
   * @see #getReturnType()
   * @generated
   */
  void setReturnType(Type value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getFunction_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link ru.iaie.reflexold.reflexOld.Function#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Arg Types</b></em>' attribute list.
   * The list contents are of type {@link ru.iaie.reflexold.reflexOld.Type}.
   * The literals are from the enumeration {@link ru.iaie.reflexold.reflexOld.Type}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg Types</em>' attribute list.
   * @see ru.iaie.reflexold.reflexOld.Type
   * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getFunction_ArgTypes()
   * @model unique="false"
   * @generated
   */
  EList<Type> getArgTypes();

} // Function
