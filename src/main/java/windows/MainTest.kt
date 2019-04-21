package windows



import org.uqbar.arena.Application
import org.uqbar.arena.windows.MainWindow
import user.Client

// Start Application
fun main() = LoginWindow().startApplication()

class MainTest : Application() {

    override fun createMainWindow(): MainWindow<*> {
        val currentUser = UserModel()
        currentAccount.available = 100

        val savingBank = SavingBank()
        savingBank.available = 10

        val bank = BankAppModel()
        bank.accounts.add(currentAccount)
        bank.accounts.add(savingBank)

        return MainWindow(this, bank)
    }
}
