/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Proc Stat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.StartProcStat#getProcess <em>Process</em>}</li>
 * </ul>
 *
 * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getStartProcStat()
 * @model
 * @generated
 */
public interface StartProcStat extends Statement
{
  /**
   * Returns the value of the '<em><b>Process</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Process</em>' reference.
   * @see #setProcess(ru.iaie.reflexold.reflexOld.Process)
   * @see ru.iaie.reflexold.reflexOld.ReflexOldPackage#getStartProcStat_Process()
   * @model
   * @generated
   */
  ru.iaie.reflexold.reflexOld.Process getProcess();

  /**
   * Sets the value of the '{@link ru.iaie.reflexold.reflexOld.StartProcStat#getProcess <em>Process</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Process</em>' reference.
   * @see #getProcess()
   * @generated
   */
  void setProcess(ru.iaie.reflexold.reflexOld.Process value);

} // StartProcStat
