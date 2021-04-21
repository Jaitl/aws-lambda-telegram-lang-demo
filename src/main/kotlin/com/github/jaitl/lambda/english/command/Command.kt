package com.github.jaitl.lambda.english.command

interface Command {
    val chatId: Long
}

data class TextCommand(override val chatId: Long, val text: String) : Command
data class VoiceCommand(override val chatId: Long, val fileId: String) : Command
data class PingCommand(override val chatId: Long) : Command

data class AddCategoryCommand(override val chatId: Long, val name: String) : Command