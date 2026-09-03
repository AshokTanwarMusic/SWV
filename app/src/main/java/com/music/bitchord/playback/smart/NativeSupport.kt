/*
 * BitChord 32-bit compatibility helper.
 *
 * The upstream 1.5 release packages the native analyzer only for arm64-v8a/x86_64.
 * This build targets armeabi-v7a, so ONNX Runtime based features that require a
 * 64-bit native runtime are deliberately disabled on 32-bit Android instead of
 * allowing an UnsatisfiedLinkError to terminate analysis/playback.
 */
package com.music.bitchord.playback.smart

import android.os.Build

object NativeSupport {
    /** True when the running Android userspace exposes any 64-bit ABI. */
    val is64Bit: Boolean = Build.SUPPORTED_64_BIT_ABIS.isNotEmpty()
    val is32Bit: Boolean get() = !is64Bit
}
