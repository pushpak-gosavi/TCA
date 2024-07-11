package com.pushpak.tca.action

/**
 * Action
 */
sealed class CounterAction {
    data object Increment: CounterAction()
    data object Decrement: CounterAction()
}