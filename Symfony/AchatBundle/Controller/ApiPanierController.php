<?php

namespace AchatBundle\Controller;

use AchatBundle\Entity\Panier;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UserBundle\Entity\User;

/**
 * ApiPanier controller.
 *
 * @Route("Apipanier")
 */
class ApiPanierController extends Controller
{

    /**
     * Lists all panier entities.
     *
     * @Route("/allpanier", name="api_panier_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $paniers = $em->getRepository('AchatBundle:Panier')->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($paniers);
        return new JsonResponse($formatted);

    }

    /**
     * Finds and displays a panier entity.
     *
     * @Route("/show", name="api_panier_show")
     * @Method("GET")
     */
    public function showAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($request->get('user'));

        $panier = $em->getRepository('AchatBundle:Panier')->findByUser_Id($user);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse($formatted);
    }


    /**
     * Deletes a panier entity.
     *
     * @Route("/{id}/supprimer", name="panier_supprimer")
     * @Method("DELETE")
     */
    public function supprimerAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $panier = $em->getRepository("AchatBundle:Panier")->find($id);

        $em->remove($panier);
        $em->flush();
        return $this->redirectToRoute('lignecommande_index');

    }

    /**
     * changer panier .
     *
     * @Route("/{id}/changer", name="api_panier_changer")
     * @Method("POST")
     */
    public function changerAction (Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $panier1 = $em->getRepository("AchatBundle:Panier")->find($request->get('id'));
        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($panier1->getUserId());

        $panier1->setEtat(false);
        $em->persist($panier1);
        $em->flush();
        $panier=new Panier();
        $panier->setUserId($user);
        $panier->setPrixtotal(0);
        $panier->setEtat(true);
        $em->persist($panier);
        $em->flush();

//        dump($panier1,$panier);
//exit();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize([$panier1,$panier]);
        return new JsonResponse($formatted);
    }



}
