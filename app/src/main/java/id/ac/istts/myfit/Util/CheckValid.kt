package id.ac.istts.myfit.Util

class CheckValid {
    companion object{
        fun isValidEmail(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
            return email.matches(emailRegex)
        }

        fun validatePassword(password: String): Boolean {
            val hasLetter = password.any { it.isLetter() }

            val hasNumberOrSpecial = password.any { !it.isLetterOrDigit() } || password.any { it.isDigit() }

            val isAtLeast10 = password.length >= 10

            return hasLetter && hasNumberOrSpecial && isAtLeast10
        }

    }
}