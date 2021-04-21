package com.github.jaitl.lambda.english.action

import com.github.jaitl.lambda.english.aws.Aws
import com.github.jaitl.lambda.english.bot.TelegramBot
import com.github.jaitl.lambda.english.command.Command
import com.github.jaitl.lambda.english.command.VoiceCommand
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

class RecognizeVoiceAction(private val aws: Aws, private val telegramBot: TelegramBot) : Action {
    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    override fun handleCommand(command: Command) {
        logger.info("recognize voice")
        val cmd = command as VoiceCommand
        val fileSteam = telegramBot.getFileStream(cmd.fileId)
        val msg = aws.transcribe(fileSteam)
        telegramBot.sendMessage(cmd.chatId, "you said: $msg")
    }

    override fun getCommandType(): KClass<out Command> {
        return VoiceCommand::class
    }
}
