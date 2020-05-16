<?php

namespace AchatBundle\Controller;

use AchatBundle\Entity\Panier;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

use UserBundle\Entity\User;

/**
 * Panier controller.
 *
 * @Route("panier")
 */
class PanierController extends Controller
{
//
//    /**
//     * Lists all panier entities.
//     *
//     * @Route("/", name="panier_index")
//     * @Method("GET")
//     */
//    public function indexAction()
//    {
//        $em = $this->getDoctrine()->getManager();
//
//        $paniers = $em->getRepository('AchatBundle:Panier')->findAll();
//
//        return $this->render('@Achat/panier/index.html.twig', array(
//            'paniers' => $paniers,
//        ));
//    }
//
//    /**
//     * Creates a new panier entity.
//     *
//     * @Route("/new", name="panier_new")
//     * @Method({"GET", "POST"})
//     */
//    public function newAction(Request $request)
//    {
//        $panier = new Panier();
//        $form = $this->createForm('AchatBundle\Form\PanierType', $panier);
//        $form->handleRequest($request);
//
//        if ($form->isSubmitted() && $form->isValid()) {
//            $em = $this->getDoctrine()->getManager();
//            $em->persist($panier);
//            $em->flush();
//
//            return $this->redirectToRoute('panier_show', array('id' => $panier->getId()));
//        }
//
//        return $this->render('@Achat/panier/new.html.twig', array(
//            'panier' => $panier,
//            'form' => $form->createView(),
//        ));
//    }
//
//    /**
//     * Finds and displays a panier entity.
//     *
//     * @Route("/{id}", name="panier_show")
//     * @Method("GET")
//     */
//    public function showAction(Panier $panier)
//    {
//        $deleteForm = $this->createDeleteForm($panier);
//
//        return $this->render('@Achat/panier/show.html.twig', array(
//            'panier' => $panier,
//            'delete_form' => $deleteForm->createView(),
//        ));
//    }
//
//    /**
//     * Displays a form to edit an existing panier entity.
//     *
//     * @Route("/{id}/edit", name="panier_edit")
//     * @Method({"GET", "POST"})
//     */
//    public function editAction(Request $request, Panier $panier)
//    {
//        $deleteForm = $this->createDeleteForm($panier);
//        $editForm = $this->createForm('AchatBundle\Form\PanierType', $panier);
//        $editForm->handleRequest($request);
//
//        if ($editForm->isSubmitted() && $editForm->isValid()) {
//            $this->getDoctrine()->getManager()->flush();
//
//            return $this->redirectToRoute('panier_edit', array('id' => $panier->getId()));
//        }
//
//        return $this->render('@Achat/panier/edit.html.twig', array(
//            'panier' => $panier,
//            'edit_form' => $editForm->createView(),
//            'delete_form' => $deleteForm->createView(),
//        ));
//    }
//
//    /**
//     * Deletes a panier entity.
//     *
//     * @Route("/{id}/delete", name="panier_delete")
//     * @Method("DELETE")
//     */
//    public function deleteAction(Request $request, Panier $panier)
//    {
//        $form = $this->createDeleteForm($panier);
//        $form->handleRequest($request);
//
//        if ($form->isSubmitted() && $form->isValid()) {
//            $em = $this->getDoctrine()->getManager();
//            $em->remove($panier);
//            $em->flush();
//        }
//
//        return $this->redirectToRoute('panier_index');
//    }
//
//    /**
//     * Creates a form to delete a panier entity.
//     *
//     * @param Panier $panier The panier entity
//     *
//     * @return \Symfony\Component\Form\Form The form
//     */
//    private function createDeleteForm(Panier $panier)
//    {
//        return $this->createFormBuilder()
//            ->setAction($this->generateUrl('panier_delete', array('id' => $panier->getId())))
//            ->setMethod('DELETE')
//            ->getForm()
//        ;
//    }

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
     * @Route("/{id}/changer", name="panier_changer")
     * @Method("GET")
     */
    public function changerAction($id,$c)
    {
        $em = $c->getDoctrine()->getManager();
        $user = $c->getUser();

        $panier1 = $em->getRepository("AchatBundle:Panier")->find($id);
//        var_dump($user2);
//        exit();

        $panier1->setEtat(false);
        $em->persist($panier1);
        $em->flush();

        $panier=new Panier();
        $panier->setUserId($user);
        $panier->setPrixtotal(0);
        $panier->setEtat(true);
        $em = $c->getDoctrine()->getManager();
        $em->persist($panier);
        $em->flush();
        return $c->redirectToRoute('lignecommande_index');

    }



}
