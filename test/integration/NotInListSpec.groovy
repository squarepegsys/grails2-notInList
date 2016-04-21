import grails.test.spock.IntegrationSpec
import grails2.notinlist.Foo
import grails2.notinlist.FooStatus
/**
 * Created by mikeh on 4/20/16.
 */

class NotInListSpec extends IntegrationSpec{

    def "by status"() {

        given:

        def goodFoo = new Foo(name:"good",status:FooStatus.GOOD).save(failOnError:true, flush:true)

        when:

        def foos =Foo.findAllByStatus(FooStatus.GOOD)

        then:

        foos.size()==1

        foos[0]==goodFoo

    }

    def "in list"() {

        given:

        def uglyFoo = new Foo(name:"ugly",status:FooStatus.UGLY).save(failOnError:true, flush:true)

        when:

        def foos = Foo.findAllByStatusInList([FooStatus.BAD,FooStatus.UGLY])

        then:

        foos.size()==1

        foos[0]==uglyFoo
    }


    def "not in list"() {
        given:

        def goodFoo = new Foo(name:"good",status:FooStatus.GOOD).save(failOnError:true, flush:true)

        when:

        def foos = Foo.findAllByStatusNotInList([FooStatus.BAD,FooStatus.UGLY])


        then:

        foos.size()==1

        foos[0]==goodFoo

    }
}
