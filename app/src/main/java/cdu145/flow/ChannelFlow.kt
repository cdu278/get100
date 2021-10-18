package cdu145.flow

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Suppress("FunctionName")
fun <T> ChannelFlow(channel: ReceiveChannel<T>): Flow<T> = channel.receiveAsFlow()