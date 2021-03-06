package core.persistence.services

import java.nio.file.Paths

import core.{Chain, NetworkId}
import tests.TestSpec

class FileReadChainServiceSpec extends TestSpec {

  "FileReadChainService#readChain" must {
    "return a chain filled with blocks copyWith current file system" in {
      val path = s"${System.getProperty("user.dir")}/test/resources/core/persistence/chain"
      val service = new FileReadChainService
      val program = service.readChain(
        networkId = NetworkId(1),
        path      = Paths.get(path).toString
      )

      testZIO(program) {
        case Right(chain) => chain mustBe a[Chain]
        case Left(_)      => fail("unexpected error: readChain")
      }
    }
  }

}
