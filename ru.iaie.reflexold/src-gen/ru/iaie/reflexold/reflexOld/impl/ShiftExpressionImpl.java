/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ru.iaie.reflexold.reflexOld.ReflexOldPackage;
import ru.iaie.reflexold.reflexOld.ShiftExpression;
import ru.iaie.reflexold.reflexOld.ShiftOp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shift Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ShiftExpressionImpl#getShiftOp <em>Shift Op</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShiftExpressionImpl extends CompareExpressionImpl implements ShiftExpression
{
  /**
   * The default value of the '{@link #getShiftOp() <em>Shift Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShiftOp()
   * @generated
   * @ordered
   */
  protected static final ShiftOp SHIFT_OP_EDEFAULT = ShiftOp.LEFT_SHIFT;

  /**
   * The cached value of the '{@link #getShiftOp() <em>Shift Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShiftOp()
   * @generated
   * @ordered
   */
  protected ShiftOp shiftOp = SHIFT_OP_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ShiftExpressionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ReflexOldPackage.Literals.SHIFT_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ShiftOp getShiftOp()
  {
    return shiftOp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setShiftOp(ShiftOp newShiftOp)
  {
    ShiftOp oldShiftOp = shiftOp;
    shiftOp = newShiftOp == null ? SHIFT_OP_EDEFAULT : newShiftOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.SHIFT_EXPRESSION__SHIFT_OP, oldShiftOp, shiftOp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ReflexOldPackage.SHIFT_EXPRESSION__SHIFT_OP:
        return getShiftOp();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ReflexOldPackage.SHIFT_EXPRESSION__SHIFT_OP:
        setShiftOp((ShiftOp)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ReflexOldPackage.SHIFT_EXPRESSION__SHIFT_OP:
        setShiftOp(SHIFT_OP_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ReflexOldPackage.SHIFT_EXPRESSION__SHIFT_OP:
        return shiftOp != SHIFT_OP_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (shiftOp: ");
    result.append(shiftOp);
    result.append(')');
    return result.toString();
  }

} //ShiftExpressionImpl