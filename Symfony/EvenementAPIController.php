<?php


namespace eventBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer ;
use Symfony\Component\HttpFoundation\Request;
use eventBundle\Entity\Evenement;

class EvenementAPIController extends Controller
{

    public function indexapiAction()
    {
        $em = $this->getDoctrine()->getManager();
        $em2 = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('eventBundle:Evenement')->findAll();
        $regions = $em2->getRepository('eventBundle:Region')->findAll();

        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($evenements) ;
        return new JsonResponse($formatted) ;
    }
    public function showapiAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $em2 = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('eventBundle:Evenement')->find($id);


        $serializer = new Serializer([new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($evenements) ;
        return new JsonResponse($formatted) ;
    }
    public function indexregionapiAction()
    {

        $em2 = $this->getDoctrine()->getManager();


        $regions = $em2->getRepository('eventBundle:Region')->findAll();

        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($regions) ;
        return new JsonResponse($formatted) ;
    }
    public function showapiregionAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $em2 = $this->getDoctrine()->getManager();

        $regions = $em->getRepository('eventBundle:Region')->find($id);


        $serializer = new Serializer([new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($regions) ;
        return new JsonResponse($formatted) ;
    }
    public function searchapiAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('p');
        $evenements =  $em->getRepository('eventBundle:Evenement')->findEntitiesByString($requestString);
        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($evenements) ;
        return new JsonResponse($formatted) ;
    }
    public function userstabAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $users = $em->getRepository('eventBundle:Evenement')->tableauuser();
        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($users) ;
        return new JsonResponse($formatted) ;
    }
    public function getusersAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $users = $em->getRepository('eventBundle:Evenement')->tableauuser2();
        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($users) ;
        return new JsonResponse($formatted) ;
    }
    public function modAction(Request $request)
    {
        //$test = $this->getUser() ;

        $id= $request->query->get('iduser');

        $eventid=$request->query->get('id');

        $user=$this->getDoctrine()->getRepository('eventBundle:User')->find($id);
        $event=$this->getDoctrine()->getRepository('eventBundle:Evenement')->find($eventid) ;
        $user->setEvent($event);
        //$em = $this->getDoctrine()->getManager();
        //$em->getRepository('eventBundle:Evenement')->eventset($test , $id);

        $em = $this->getDoctrine()->getManager();
        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([ new ObjectNormalizer()]) ;
        $formatted = $serializer->normalize($user) ;
        return new JsonResponse($formatted) ;

    }
}