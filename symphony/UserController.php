<?php


namespace UserBundle\Controller;


use Doctrine\ORM\Query;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use UserBundle\Entity\User;
use Symfony\Component\Serializer\Serializer;



class UserController extends Controller
{
    public function insertAction(Request $request)
    {
        $id= $request->query->get('id');
       $username= $request->query->get('username');
        $email= $request->query->get('email');
        $enabled= $request->query->get('enabled');
        $salt= $request->query->get('salt');
        $password=$request->query->get('password');
        $lastLogin=$request->query->get('lastlogin');
        $confirmationToken=$request->query->get('confirmationtoken');
        $passwordRequestedAt=$request->query->get('passwordRequestedAt');
 $roles=$request->query->get('roles');
$user = new User();

$user->setId($id);
  $user->setUsername($username);
  $user->setUsernameCanonical($username);
   $user->setEmail($email);
   $user->setEmailCanonical($email);
    $user->setEnabled($enabled);
 $user->setSalt($salt);
 $user->setPassword($password);
 $user->setLastLogin($lastLogin);
 $user->setConfirmationToken($confirmationToken);
 $user->setPasswordRequestedAt($passwordRequestedAt);
 $user->setRoles(array($roles));
        $em = $this->getDoctrine()->getManager();
        $em->persist($user);
        $em->flush();

          return new Response("ok");
    }
    public function getAction(Request $request){

        $query = $this->getDoctrine()
            ->getRepository(User::class)
            ->createQueryBuilder('c')
            ->getQuery();
        $result = $query->getResult(Query::HYDRATE_ARRAY);

        return new jsonResponse($result);
    }
    public function rechAction(Request $request){
        $username= $request->query->get('username');
      //  $user = $this->getDoctrine()
            //->getRepository(User::class)->findBy(array('username'=> $username));

      // return new jsonResponse($user);
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
                "select A from UserBundle:User  A where A.username= :id"
            )->setParameter('id',$username);
        $result = $query->getResult(Query::HYDRATE_ARRAY);

        return new jsonResponse($result);


    }
    public function modAction(Request $request)
    {
        $id= $request->query->get('id');
        $username= $request->query->get('username');
        $email= $request->query->get('email');
        $enabled= $request->query->get('enabled');
        $salt= $request->query->get('salt');
        $password=$request->query->get('password');
        $lastLogin=$request->query->get('lastlogin');
        $confirmationToken=$request->query->get('confirmationtoken');
        $passwordRequestedAt=$request->query->get('passwordRequestedAt');
        $roles=$request->query->get('roles');

        $user=$this->getDoctrine()->getRepository(User::class)->find($id);

        $user->setUsername($username);
        $user->setUsernameCanonical($username);
        $user->setEmail($email);
        $user->setEmailCanonical($email);
        $user->setEnabled($enabled);
        $user->setSalt($salt);
        $user->setPassword($password);
        $user->setLastLogin($lastLogin);
        $user->setConfirmationToken($confirmationToken);
        $user->setPasswordRequestedAt($passwordRequestedAt);
        $user->setRoles(array($roles));

        $em = $this->getDoctrine()->getManager();
        $em->persist($user);
        $em->flush();

        return new Response("ok");
    }
}